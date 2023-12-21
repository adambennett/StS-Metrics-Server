package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.services.ConfigDifferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class ConfigDifferenceController {

    private static ConfigDifferenceService service;

    private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.ConfigDifferenceController");

    @Autowired
    public ConfigDifferenceController(ConfigDifferenceService serv) {
        service = serv;
    }

    public static ConfigDifferenceService getService() {
        return service;
    }
}
