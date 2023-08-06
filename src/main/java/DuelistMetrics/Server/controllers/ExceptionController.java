package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.LoggedException;
import DuelistMetrics.Server.models.dto.LoggedExceptionDTO;
import DuelistMetrics.Server.services.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ExceptionController {

    private static final Logger logger = Logger.getLogger("DuelistMetrics.ExceptionController");

    private final ExceptionService exceptionService;

    @Autowired
    public ExceptionController(ExceptionService expService) {
        exceptionService = expService;
    }

    @PostMapping({"/searchLogsByMessage", "/searchLogsByMessage/{days}"})
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public List<LoggedExceptionDTO> findLogsByMessage(@RequestBody String message, @PathVariable(required = false) String days) {
        if (days != null) {
            try {
                Integer daysParsed = Integer.parseInt(days);
                return this.exceptionService.searchLogsByMessageDays(message, daysParsed);
            } catch (Exception ex) {
                return this.exceptionService.searchLogsByMessage(message);
            }
        }
        return this.exceptionService.searchLogsByMessage(message);
    }

    @GetMapping("/lastXDaysOfLogs/{days}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public Object findLogsByTrace(@PathVariable String days) {
        try {
            Integer daysParsed = Integer.parseInt(days);
            return this.exceptionService.findLastXDaysOfLogs(daysParsed);
        } catch (Exception ex) {
            return "Exception while fetching logs - " + ex.getMessage();
        }
    }

    @PostMapping("/logException")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public String handleException(@RequestBody LoggedExceptionDTO exception) {
        this.exceptionService.create(new LoggedException(
                exception.message(),
                exception.stackTrace(),
                exception.uuid(),
                new Date(),
                exception.duelistModVersion(),
                exception.devMessage(),
                exception.runUUID()
        ));
        logger.info("Saved exception - " + exception.message());
        return "";
    }


}
