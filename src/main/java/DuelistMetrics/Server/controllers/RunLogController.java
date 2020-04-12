package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
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

    @GetMapping("/Decks")
    public static Collection<DisplayDeck> getDeckCompare() {
      List<DisplayDeck> output = new ArrayList<>();
      Map<String, Integer> a20Wins = getService().getA20Wins();
      Map<String, Integer> a20Runs = getService().getA20Runs();
      Map<String, Integer> c20Wins = getService().getC20Wins();
      Map<String, Integer> c20Runs = getService().getC20Runs();
      Map<String, Integer> runs = getService().getRuns();
      Map<String, Integer> wins = getService().getWins();
      Map<String, Integer> kaiba = getService().getKaibaRuns();
      List<DeckKilledBy> killed = getService().getMostKilledBy();
      Map<String, Integer> floor = getService().getHighestFloor();
      Map<String, Integer> highestChal = getService().getHighestChallenge();
      Map<String, String> deckToKilledBy = new HashMap<>();
      for (DeckKilledBy dkb : killed) {
        deckToKilledBy.put(dkb.getDeck(), dkb.getKilled_by() + " (" + dkb.getCount() + ")");
      }
      ArrayList<String> decks = new ArrayList<>();
      for (Map.Entry<String, Integer> entry : a20Runs.entrySet()) {
        decks.add(entry.getKey());
      }
      for (String deckName : decks) {
        DisplayDeck deck = new DisplayDeckBuilder()
          .setDeck(deckName)
          .setA20runs(a20Runs.get(deckName))
          .setA20wins(a20Wins.get(deckName))
          .setC20runs(c20Runs.get(deckName))
          .setC20wins(c20Wins.get(deckName))
          .setFloor(floor.get(deckName))
          .setKaiba(kaiba.get(deckName))
          .setRuns(runs.get(deckName))
          .setWins(wins.get(deckName))
          .setMostKilledBy(deckToKilledBy.get(deckName))
          .setHighestChallenge(highestChal.get(deckName))
          .createDisplayDeck();
        if (deck.getC20runs() == null) { deck.setC20runs(0); }
        if (deck.getC20wins() == null) { deck.setC20wins(0); }
        if (deck.getKaiba() == null) { deck.setKaiba(0); }
        output.add(deck);
      }
      Collections.sort(output);
      return output;
    }

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
      BundleProcessor.parse(run, true, true);
      return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
