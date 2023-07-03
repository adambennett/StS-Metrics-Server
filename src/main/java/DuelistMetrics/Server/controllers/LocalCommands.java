package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.*;

@RestController
public class LocalCommands {

    private static final Logger logger = Logger.getLogger("Server");

    private static InfoService info;
    private static BundleService bundle;
    private static RunLogService runs;

    @Autowired
    public LocalCommands(InfoService infoService, BundleService bundleService, RunLogService runLogService) {
        info = infoService;
        bundle = bundleService;
        runs = runLogService;
    }

    public static void updateDuelistEvents() {
        List<String> names = new ArrayList<>();
        names.add("Millennium Items");
        names.add("Relic Duplicator");
        names.add("Card Trader");
        names.add("Visit from Anubis");
        names.add("Aknamkanon's Tomb");
        names.add("Tomb of the Nameless");
        names.add("Nameless Tomb");
        names.add("Battle City");
        names.add("Egyptian Village");
        info.updateAllDuelistEvents(names);
    }

}
