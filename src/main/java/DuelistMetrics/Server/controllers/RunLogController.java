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
public class RunLogController {

    private static RunLogService bundles;

    @Autowired
    public RunLogController(RunLogService service) { bundles = service; }

    public static RunLogService getService() { return bundles; }

    @GetMapping("/Runs")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getBundles(){
      return bundles.findAll();
    }

    @GetMapping("/Runs/pages")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Page<RunLog> getBundles(Pageable pageable)
    {
      return bundles.findAllPages(pageable);
    }

    @GetMapping("/Runs/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getBundle(@PathVariable Long id) {
        Optional<RunLog> p = bundles.findById(id);
        return (p.isPresent()) ? new ResponseEntity<> (p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Valid
    @PostMapping("/Runs")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> save(@RequestBody RunLog run) {
        run = bundles.create(run);
        URI newPostUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(run.getRun_id())
                .toUri();

        return new ResponseEntity<>(newPostUri, HttpStatus.CREATED);
    }

    @PutMapping("/upload")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> upload(@RequestBody TopBundle run)
    {
      BundleProcessor.parse(run);
      return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
