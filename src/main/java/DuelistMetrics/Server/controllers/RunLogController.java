package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.models.infoModels.*;
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

    @PostMapping("/runupload")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> upload(@RequestBody TopBundle run)
    {
        if (run != null) {
            BundleProcessor.parse(run, true, true);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/runs")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getBundles(){
        return bundles.findAll();
    }

    @GetMapping("/runs/{character}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getBundles(@PathVariable String character){
        return bundles.getAllByChar(character);
    }

    @GetMapping("/allCharacters")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<String> getCharacters() {
        return bundles.getAllCharacters();
    }

    @GetMapping("/decks")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<DisplayDeck> getDeckCompare() {
      List<DisplayDeck> output = new ArrayList<>();

      Optional<Long> allA20W = getService().getA20WinsAll();
      Long allA20Wins = allA20W.orElse(0L);
      Long allA20Runs = getService().getA20RunsAll();
      Long allC20Wins = getService().getC20WinsAll();
      Long allC20Runs = getService().getC20RunsAll();
      Long allRuns = getService().getRunsAll();
      Long allWins = getService().getWinsAll();
      Long allKaiba = getService().getKaibaRunsAll();
      if (allC20Wins == null) { allC20Wins = 0L; }
      if (allC20Runs == null) { allC20Runs = 0L; }
      List<String> allKilledList =  getService().getMostKilledByAll();
      String allKilled ="";
      if (allKilledList.size() > 0) {
          String[] splice = allKilledList.get(0).split(",");
          allKilled = splice[0] + " (" + splice[1] + ")";
      }
      Long allFloor = getService().getHighestFloorAll();
      Optional<Long> highestChallenge = getService().getHighestChallengeAll();
      DisplayDeck allDeck = new DisplayDeckBuilder()
                .setDeck("All")
                .setA20runs(Math.toIntExact(allA20Runs))
                .setA20wins(Math.toIntExact(allA20Wins))
                .setC20runs(Math.toIntExact(allC20Runs))
                .setC20wins(Math.toIntExact(allC20Wins))
                .setFloor(Math.toIntExact(allFloor))
                .setKaiba(Math.toIntExact(allKaiba))
                .setRuns(Math.toIntExact(allRuns))
                .setWins(Math.toIntExact(allWins))
                .setMostKilledBy(allKilled)
                .setHighestChallenge(Math.toIntExact(highestChallenge.orElse(-1L)))
                .createDisplayDeck();

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
       /* Optional<InfoCreature> dbCreature = InfoController.getCreature(dkb.getKilled_by());
        String creature = "";
        if (dbCreature.isPresent()) {
            creature = dbCreature.get().getName();
        } else {
            creature = dkb.getKilled_by();
        }*/
        deckToKilledBy.put(dkb.getDeck(), dkb.getKilled_by() + " (" + dkb.getCount() + ")");
      }
      ArrayList<String> decks = new ArrayList<>();
      for (Map.Entry<String, Integer> entry : runs.entrySet()) {
        decks.add(entry.getKey());
      }
      for (String deckName : decks) {
        String dName = deckName;
        if (deckName.equals("NotYugi")) {
            dName = "Non-Duelist Character";
        }
        DisplayDeck deck = new DisplayDeckBuilder()
          .setDeck(dName)
          .setA20runs(a20Runs.getOrDefault(deckName, 0))
          .setA20wins(a20Wins.getOrDefault(deckName, 0))
          .setC20runs(c20Runs.getOrDefault(deckName, 0))
          .setC20wins(c20Wins.getOrDefault(deckName, 0))
          .setFloor(floor.getOrDefault(deckName, 0))
          .setKaiba(kaiba.get(deckName))
          .setRuns(runs.getOrDefault(deckName, 0))
          .setWins(wins.getOrDefault(deckName, 0))
          .setMostKilledBy(deckToKilledBy.getOrDefault(deckName, "Unknown"))
          .setHighestChallenge(highestChal.getOrDefault(deckName, -1))
          .createDisplayDeck();
        if (deck.getC20runs() == null) { deck.setC20runs(0); }
        if (deck.getC20wins() == null) { deck.setC20wins(0); }
        if (deck.getKaiba() == null) { deck.setKaiba(0); }
        output.add(deck);
      }
      Collections.sort(output);
      output.add(0, allDeck);
      return output;
    }
}
