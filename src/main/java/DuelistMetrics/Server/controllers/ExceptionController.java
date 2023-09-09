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

    private static ExceptionService exceptionService;

    @Autowired
    public ExceptionController(ExceptionService expService) {
        exceptionService = expService;
    }

    public static ExceptionService getService() {
        return exceptionService;
    }

    @PostMapping({
            "/searchLogsByMessage",
            "/searchLogsByMessage/{days}",
            "/searchLogsByMessage/{version}",
            "/searchLogsByMessage/{days}/{version}"
    })
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public List<LoggedExceptionDTO> findLogsByMessage(@RequestBody String message, @PathVariable(required = false) String days, @PathVariable(required = false) String version) {
        if (version != null && days != null) {
            try {
                Integer daysParsed = Integer.parseInt(days);
                return exceptionService.searchLogsByMessageDays(message, daysParsed, version);
            } catch (Exception ex) {
                return exceptionService.searchLogsByMessage(message, version);
            }
        } else if (version != null) {
            return exceptionService.searchLogsByMessage(message, version);
        } else if (days != null) {
            try {
                Integer daysParsed = Integer.parseInt(days);
                return exceptionService.searchLogsByMessageDays(message, daysParsed);
            } catch (Exception ex) {
                return exceptionService.searchLogsByMessage(message);
            }
        }
        return exceptionService.searchLogsByMessage(message);
    }

    @GetMapping({"/lastXDaysOfLogs/{days}", "/lastXDaysOfLogs/{days}/{version}"})
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public Object findLogsByTrace(@PathVariable String days, @PathVariable(required = false) String version) {
        try {
            Integer daysParsed = Integer.parseInt(days);
            return version == null
                    ? exceptionService.findLastXDaysOfLogs(daysParsed)
                    : exceptionService.findLastXDaysOfLogs(daysParsed, version);
        } catch (Exception ex) {
            return "Exception while fetching logs - " + ex.getMessage();
        }
    }

    @PostMapping("/logException")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public String handleException(@RequestBody LoggedExceptionDTO exception) {
        exceptionService.create(new LoggedException(
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

    @PostMapping("/logWebException")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public String handleWebException(@RequestBody LoggedExceptionDTO exception) {
        exceptionService.create(new LoggedException(
                "Website",
                exception.devMessage(),
                exception.message(),
                exception.stackTrace(),
                exception.uuid(),
                exception.duelistModVersion(),
                exception.runUUID(),
                new Date()
        ));
        logger.info("Saved exception - " + exception.message());
        return "";
    }


}
