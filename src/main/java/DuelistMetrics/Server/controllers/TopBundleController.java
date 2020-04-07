package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
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

    @GetMapping("/metrics")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<TopBundle> getBundles(){
        return bundles.findAll();
    }

    @GetMapping("/metrics/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getPost(@PathVariable Long id) {
        Optional<TopBundle> p = bundles.findById(id);
        return (p.isPresent()) ? new ResponseEntity<> (p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Valid
    @PostMapping("/metrics")
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


    @PutMapping("/metrics/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> editPost(@RequestBody TopBundle run, @PathVariable Long id)
    {
       // bundles.update(run, id);
        return new ResponseEntity<>(run, HttpStatus.OK);
    }

    @DeleteMapping("/metrics/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        bundles.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
