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

    @GetMapping("/Metrics")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<PickInfo> getBundles(){
        return bundles.findAll();
    }

    @GetMapping("/Metrics/pages")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Page<PickInfo> getBundles(Pageable pageable)
    {
      return bundles.findAllPages(pageable);
    }

    @GetMapping("/Metrics/{id}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getBundle(@PathVariable Long id) {
        Optional<PickInfo> p = bundles.findById(id);
        return (p.isPresent()) ? new ResponseEntity<> (p, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Valid
    @PostMapping("/Metrics")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> save(@RequestBody PickInfo run) {
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
    public ResponseEntity<?> upload(@RequestBody PickInfo run)
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
