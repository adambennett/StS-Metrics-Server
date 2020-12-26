package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.config.*;
import DuelistMetrics.Server.models.tierScore.*;
import DuelistMetrics.Server.repositories.*;
import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
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

    @Autowired
    public ScheduledUpdater(InfoService infoService, CustomProperties env) {
        this.infoService = infoService;
        this.env = env;
        this.logProgress = env.showUpdateProgress;
    }

    @GetMapping("/checkScheduler")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> isSchedulerRunning() {
        return new ResponseEntity<>(isUpdating, HttpStatus.OK);
    }

    @Scheduled(fixedDelay = ONE_DAY * 7, initialDelay = 1000)
    public void calculateTierScores() {
        if (env.enableAutomaticUpdates) {
            isUpdating = true;
            Logger.getGlobal().info("Updating tier scores. Calculating, please wait.");
            long startTime = System.nanoTime();
            try {
                Map<String, String> cache = new HashMap<>();
                Map<String, List<ScoredCard>> scores = InfoController.calculateTierScores(-2, -1, "any");
                Logger.getGlobal().info("Done calculating tier scores. Updating database with new entries.");
                for (Map.Entry<String, List<ScoredCard>> entry : scores.entrySet()) {
                    String pool = entry.getKey();
                    int size = entry.getValue().size();
                    Logger.getGlobal().info("Updating tier scores for pool: " + pool + " (" + size + ")");
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
                        InfoController.saveTierScores(card);
                        if (logProgress) {
                            Logger.getGlobal().info(pool + " Progress: [" + counter + " / " + size + "]");
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
                Logger.getGlobal().info("Tier scores updated. Execution time: " + minutes + "m " + remainderSeconds + "s");
            } catch (Exception ex) {
                Logger.getGlobal().info("Error running scheduler!");
            }
        }
    }
}
