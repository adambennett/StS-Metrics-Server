package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.services.FailedRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class FailedRunController {

    private static FailedRunService service;

    private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.FailedRunController");

    @Autowired
    public FailedRunController(FailedRunService serv) {
        service = serv;
    }

    public static FailedRunService getService() {
        return service;
    }
}
