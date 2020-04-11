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
public class BundleController {

    private static BundleService bundles;

    @Autowired
    public BundleController(BundleService service) { bundles = service; }

    public static BundleService getService() { return bundles; }

    @GetMapping("/Bundles")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<TopBundle> getBundles(){
      return bundles.findAll();
    }

    @GetMapping("/Bundles/pages")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Page<TopBundle> getBundles(Pageable pageable)
    {
      return bundles.findAllPages(pageable);
    }

    @GetMapping("/Bundles/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getBundle(@PathVariable Long id) {
        Optional<TopBundle> p = bundles.findById(id);
        return (p.isPresent()) ? new ResponseEntity<> (p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Valid
    @PostMapping("/Bundles")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> save(@RequestBody TopBundle run) {
        run = bundles.create(run);
        URI newPostUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(run.getTop_id())
                .toUri();

        return new ResponseEntity<>(newPostUri, HttpStatus.CREATED);
    }
}
