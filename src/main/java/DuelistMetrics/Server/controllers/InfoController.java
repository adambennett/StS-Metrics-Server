package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import javax.validation.*;
import java.net.*;
import java.util.*;

@RestController
public class InfoController {

    private static InfoService bundles;

    @Autowired
    public InfoController(InfoService service) { bundles = service; }

    public static InfoService getService() { return bundles; }

    @RequestMapping("/error")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static String getError() {
        return "This is just the server.. perhaps you were trying to instead visit the metrics display site? That can be view at: https://sts-metrics-site.herokuapp.com/";
    }
}
