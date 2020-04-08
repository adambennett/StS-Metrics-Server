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
public class TopBundleController {

    private static TopBundleService bundles;

    @Autowired
    public TopBundleController(TopBundleService service) { bundles = service; }

    public static TopBundleService getService() { return bundles; }

    @GetMapping("/Metrics/number")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Integer getDBSize(Pageable pageable) {
      Page<TopBundle> pages = bundles.findAllPages(pageable);
      return pages.getTotalPages();
    }

    @GetMapping("/Metrics")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<TopBundle> getBundles(){
        return bundles.findAll();
    }

    @GetMapping("/Metrics/pages")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Page<TopBundle> getBundles(Pageable pageable)
    {
      return bundles.findAllPages(pageable);
    }

    @GetMapping("/Metrics/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getBundle(@PathVariable Long id) {
        Optional<TopBundle> p = bundles.findById(id);
        return (p.isPresent()) ? new ResponseEntity<> (p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Valid
    @PostMapping("/Metrics")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> save(@RequestBody TopBundle run) {
        run = bundles.create(run);
        URI newPostUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(run.getId())
                .toUri();

        return new ResponseEntity<>(newPostUri, HttpStatus.CREATED);
    }

    @PutMapping("/Metrics")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> upload(@RequestBody TopBundle run)
    {
      run = bundles.create(run);
      URI newPostUri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(run.getId())
        .toUri();

      return new ResponseEntity<>(newPostUri, HttpStatus.CREATED);
    }

    @DeleteMapping("/Metrics/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        bundles.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
