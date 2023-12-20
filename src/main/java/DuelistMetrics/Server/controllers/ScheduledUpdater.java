package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.config.*;
import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.enums.ScoringRunLookupType;
import DuelistMetrics.Server.models.tierScore.*;
import DuelistMetrics.Server.services.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.logging.*;

import static DuelistMetrics.Server.models.enums.ScoringRunLookupType.*;

@RestController
public class ScheduledUpdater {

    private static final long TEN_MINUTES  =       360_000L;
    private static final long ONE_HOUR     =     3_600_000L;
    private static final long ONE_DAY      =    86_400_000L;
    private static final long ONE_MONTH    = 2_592_000_000L;
    private static final long THREE_MONTHS = 7_776_000_000L;

    private final InfoService infoService;
    private final CustomProperties env;
    private boolean isUpdating = false;
    private final boolean logProgress;
    private final boolean logUpdatedCards;
    private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.AutoUpdateScores");

    @Autowired
    public ScheduledUpdater(InfoService infoService, CustomProperties env) {
        this.infoService = infoService;
        this.env = env;
        this.logProgress = env.showUpdateProgress;
        this.logUpdatedCards = env.showCardsUpdated;
    }

    @GetMapping("/checkScheduler")
    @CrossOrigin(origins = {"https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> isSchedulerRunning() {
        return new ResponseEntity<>(isUpdating, HttpStatus.OK);
    }

    @Scheduled(fixedDelay = ONE_DAY, initialDelay = 100)
    public void calculateTierScores() {
        if (env.enableAutomaticUpdates) {
            isUpdating = true;
            logger.info("Updating tier scores. Calculating, please wait.");
            try {
                logger.info("Calculating V4 scores, please wait...");
                Map<String, String> cache = new HashMap<>();
                long startTime = System.nanoTime();
                Map<String, List<ScoredCardV4>> v4Scores = InfoController.calculateTierScores(-2, -1, "any", V4);
                saveScores("v4", V4, v4Scores, cache, startTime);

                logger.info("Calculating A20 scores, please wait...");
                startTime = System.nanoTime();
                Map<String, List<ScoredCardA20>> a20Scores = InfoController.calculateTierScores(-2, -1, "any", A20);
                saveScores("A20", A20, a20Scores, cache, startTime);

                logger.info("Calculating Legacy scores, please wait...");
                startTime = System.nanoTime();
                Map<String, List<ScoredCard>> legacyScores = InfoController.calculateTierScores(-2, -1, "any", LEGACY);
                saveScores("Legacy", LEGACY, legacyScores, cache, startTime);

                logger.info("Done saving all tier score updates. Scheduler will now exit.");
            } catch (Exception ex) {
                logger.info("Error running scheduler! Ex:\n" + ex);
            }
        }
    }

    private <T extends GeneralScoringCard> void saveScores(String name, ScoringRunLookupType type, Map<String, List<T>> scores, Map<String, String> cache, long startTime) {
        try {
            logger.info("Done calculating " + name + " tier scores. Updating database with new entries.");
            int changedCards = 0;
            int newlyScored = 0;
            for (Map.Entry<String, List<T>> entry : scores.entrySet()) {
                String pool = entry.getKey();
                int size = entry.getValue().size();
                logger.info("Updating " + name + " tier scores for pool: " + pool + " (" + size + ")");
                Map<String, String> cardNames = null;
                int counter = 1;
                for (T card : entry.getValue()) {
                    boolean set = false;

                    // Check cache first always
                    if (cache.containsKey(card.getCard_id())) {
                        card.setCard_name(cache.get(card.getCard_id()));
                        set = true;
                    }
                    // If cache miss, make sure card names is filled
                    else if (cardNames == null) {
                        cardNames = infoService.getCardNamesByPool(pool);
                    }

                    // Cache miss
                    if (!set) {
                        // Check pool for card name
                        if (cardNames.containsKey(card.getCard_id())) {
                            card.setCard_name(cardNames.get(card.getCard_id()));
                        }
                        // Ultimately, just look it up
                        else {
                            String cardName = infoService.getCardName(card.getCard_id(), true);
                            card.setCard_name(cardName);
                            cache.put(card.getCard_id(), cardName);
                        }
                    }
                    card.setLastUpdated(new Date());
                    if (this.logUpdatedCards) {
                        TierScoreLookup oldScores = switch (type) {
                            case LEGACY -> infoService.getLegacyCardTierScores(card.getCard_id(), card.getPool_name());
                            case V4 -> infoService.getV4CardTierScores(card.getCard_id(), card.getPool_name());
                            case A20 -> infoService.getA20CardTierScores(card.getCard_id(), card.getPool_name());
                        };
                        if (oldScores == null) {
                            logger.info(name + " Scores: New card scored for " + card.getPool_name() + " -- " + card.getCard_name() + " (" + card.getCard_id() + ")");
                            newlyScored++;
                        } else {
                            int act0Diff = 0;
                            int act1Diff = 0;
                            int act2Diff = 0;
                            int act3Diff = 0;
                            int overallDiff = 0;
                            if (oldScores.getAct0_score() != card.getAct0_score()) {
                                act0Diff = card.getAct0_score() - oldScores.getAct0_score();
                            }
                            if (oldScores.getAct1_score() != card.getAct1_score()) {
                                act1Diff = card.getAct1_score() - oldScores.getAct1_score();
                            }
                            if (oldScores.getAct2_score() != card.getAct2_score()) {
                                act2Diff = card.getAct2_score() - oldScores.getAct2_score();
                            }
                            if (oldScores.getAct3_score() != card.getAct3_score()) {
                                act3Diff = card.getAct3_score() - oldScores.getAct3_score();
                            }
                            if (oldScores.getOverall_score() != card.getOverall_score()) {
                                overallDiff = card.getOverall_score() - oldScores.getOverall_score();
                            }
                            if (act0Diff > 0 || act1Diff > 0 || act2Diff > 0 || act3Diff > 0 || overallDiff > 0) {
                                changedCards++;
                                logger.info(new TierScoreCompare<>(oldScores, card, act0Diff, act1Diff, act2Diff, act3Diff, overallDiff).print(card));
                            }
                        }
                    }

                    if (card instanceof ScoredCard scoredCard) {
                        InfoController.saveTierScores(scoredCard);
                    } else if (card instanceof ScoredCardV4 scoredCardV4) {
                        InfoController.saveTierScores(scoredCardV4);
                    } else if (card instanceof ScoredCardA20 scoredCardA20) {
                        InfoController.saveTierScores(scoredCardA20);
                    }

                    if (logProgress) {
                        logger.info(name + " Scores - " + pool + " Progress: [" + counter + " / " + size + "]");
                    }
                    counter++;
                }
            }
            isUpdating = false;
            long stopTime = System.nanoTime();
            long elapsedTime = stopTime - startTime;
            double seconds = (double)elapsedTime / 1_000_000_000.0;
            double minutes = seconds / 60.0;
            double diff = minutes - ((int) (seconds / 60));
            double remainderSeconds = Math.floor(diff * 60);
            if (minutes < 1) {
                minutes = 0;
            }
            int mins = (int) minutes;
            int secs = (int)remainderSeconds;
            var message = this.logUpdatedCards
                    ? name + " Tier scores updated. " + changedCards + " card scores modified. " + newlyScored + " new cards scored. Execution time: " + mins + "m " + secs + "s"
                    : name + " Tier scores updated. Execution time: " + mins + "m " + secs + "s";
            logger.info(message);
        } catch (Exception ex) {
            logger.info("Error saving updated " + name + " scores during scheduler!\n" + ExceptionUtils.getStackTrace(ex));
        }
    }
}
