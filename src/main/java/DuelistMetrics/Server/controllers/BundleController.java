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
public class BundleController {

    private static BundleService bundles;

    @Autowired
    public BundleController(BundleService service) { bundles = service; }

    public static BundleService getService() { return bundles; }

    @GetMapping("/bundles")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<Bundle> getBundles(){
        return bundles.findAll();
    }

    @GetMapping("/bundles/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getPost(@PathVariable Long id) {
        Optional<Bundle> p = bundles.findById(id);
        return (p.isPresent()) ? new ResponseEntity<> (p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Valid
    @PostMapping("/bundles")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> save(@RequestBody Bundle run) {
        run = bundles.create(run);
        URI newPostUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(run.getId())
                .toUri();

        return new ResponseEntity<>(newPostUri, HttpStatus.CREATED);
    }


    @PutMapping("/bundles/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> editPost(@RequestBody Bundle run, @PathVariable Long id)
    {
       // bundles.update(run, id);
        return new ResponseEntity<>(run, HttpStatus.OK);
    }

    @DeleteMapping("/bundles/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        bundles.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
