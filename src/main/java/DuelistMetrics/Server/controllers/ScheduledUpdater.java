package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.tierScore.*;
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

    private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.ScheduledUpdater");

    private boolean isUpdating = false;

    @GetMapping("/checkScheduler")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> isSchedulerRunning() {
        return new ResponseEntity<>(isUpdating, HttpStatus.OK);
    }

    @Scheduled(fixedDelay = ONE_DAY, initialDelay = THREE_MONTHS)
    public void calculateTierScores() {
        isUpdating = true;
        logger.info("Scheduler started. Updating tier scores.");
        long startTime = System.nanoTime();
        try {
            Map<String, List<ScoredCard>> scores = InfoController.calculateTierScores(-2, -1, "any");
            for (Map.Entry<String, List<ScoredCard>> entry : scores.entrySet()) {
                logger.info("Updating tier scores for pool: " + entry.getKey());
                for (ScoredCard card : entry.getValue()) {
                    InfoController.saveTierScores(card);
                }
            }
            isUpdating = false;
            long stopTime = System.nanoTime();
            long elapsedTime = stopTime - startTime;
            double seconds = (double)elapsedTime / 1_000_000_000.0;
            logger.info("Scheduler has completed scoring cards. Execution time: " + seconds + " seconds");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error running scheduler!");
        }
    }
}
