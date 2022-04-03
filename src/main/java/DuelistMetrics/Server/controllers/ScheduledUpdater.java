package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.config.*;
import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.tierScore.*;
import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.logging.*;

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
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> isSchedulerRunning() {
        return new ResponseEntity<>(isUpdating, HttpStatus.OK);
    }

    @Scheduled(fixedDelay = ONE_DAY, initialDelay = 100)
    public void calculateTierScores() {
        if (env.enableAutomaticUpdates) {
            isUpdating = true;
            logger.info("Updating tier scores. Calculating, please wait.");
            long startTime = System.nanoTime();
            try {
                Map<String, String> cache = new HashMap<>();
                Map<String, List<ScoredCard>> scores = InfoController.calculateTierScores(-2, -1, "any");
                logger.info("Done calculating tier scores. Updating database with new entries.");
                int changedCards = 0;
                int newlyScored = 0;
                for (Map.Entry<String, List<ScoredCard>> entry : scores.entrySet()) {
                    String pool = entry.getKey();
                    int size = entry.getValue().size();
                    logger.info("Updating tier scores for pool: " + pool + " (" + size + ")");
                    Map<String, String> cardNames = null;
                    int counter = 1;
                    for (ScoredCard card : entry.getValue()) {
                        boolean set = false;

                        // Check cache first always
                        if (cache.containsKey(card.card_id)) {
                            card.setCard_name(cache.get(card.card_id));
                            set = true;
                        }
                        // If cache miss, make sure card names is filled
                        else if (cardNames == null) {
                            cardNames = infoService.getCardNamesByPool(pool);
                        }

                        // Cache miss
                        if (!set) {
                            // Check pool for card name
                            if (cardNames.containsKey(card.card_id)) {
                                card.setCard_name(cardNames.get(card.card_id));
                            }
                            // Ultimately, just look it up
                            else {
                                String name = infoService.getCardName(card.card_id, true);
                                card.setCard_name(name);
                                cache.put(card.card_id, name);
                            }
                        }
                        card.setLastUpdated(new Date());
                        if (this.logUpdatedCards) {
                            TierScoreLookup oldScores = infoService.getCardTierScores(card.card_id, card.pool_name);
                            if (oldScores == null) {
                                logger.info("New card scored for " + card.pool_name + " -- " + card.card_name + " (" + card.card_id + ")");
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
                                    logger.info(new TierScoreCompare(oldScores, card, act0Diff, act1Diff, act2Diff, act3Diff, overallDiff).print(card));
                                }
                            }
                        }
                        InfoController.saveTierScores(card);
                        if (logProgress) {
                            logger.info(pool + " Progress: [" + counter + " / " + size + "]");
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
                var message = this.logUpdatedCards
                        ? "Tier scores updated. " + changedCards + " card scores modified. " + newlyScored + " new cards scored. Execution time: " + minutes + "m " + remainderSeconds + "s"
                        : "Tier scores updated. Execution time: " + minutes + "m " + remainderSeconds + "s";
                logger.info(message);
            } catch (Exception ex) {
                logger.info("Error running scheduler! Ex:\n" + ex);
            }
        }
    }
}
