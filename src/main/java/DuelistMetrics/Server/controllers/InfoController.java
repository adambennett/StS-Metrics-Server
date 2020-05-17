package DuelistMetrics.Server.controllers;


import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.services.*;
import com.vdurmont.semver4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.logging.*;

@RestController
public class InfoController {

    private static InfoService bundles;
    private static Map<String, InfoCard> cardData;
    private static Map<String, InfoRelic> relicData;
    private static Map<String, InfoPotion> potionData;

    @Autowired
    public InfoController(InfoService service) {
        bundles = service;
        fillAllData();
    }

    public static InfoService getService() { return bundles; }

    @PostMapping("/dataupload")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> infoUpload(@RequestBody TopInfoBundle list){
        if (list != null) {
            List<ModInfoBundle> saved = new ArrayList<>(list.getInfo().size());
            for (ModInfoBundle mod : list.getInfo()) {
                saved.add(bundles.createBundle(mod));
            }
            if (saved.size() > 0) {
                fillAllData();
                return new ResponseEntity<>(saved, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/allModuleVersions")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getTrackedVersions() {
        List<String> versions = bundles.getAllModuleVersions();
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    public static Optional<InfoCard> getCard(String game_id) {
        if (cardData.containsKey(game_id)) { return Optional.of(cardData.get(game_id)); }
        return Optional.empty();
    }

    public static Optional<InfoRelic> getRelic(String game_id) {
        if (relicData.containsKey(game_id)) { return Optional.of(relicData.get(game_id)); }
        return Optional.empty();
    }

    public static Optional<InfoPotion> getPotion(String game_id) {
        if (potionData.containsKey(game_id)) { return Optional.of(potionData.get(game_id)); }
        return Optional.empty();
    }

    private static void fillAllData() {
        String cardOut = fillCardData();
        String relicOut = fillRelicData();
        String potionOut = fillPotionData();
        Logger.getGlobal().info(cardOut);
        Logger.getGlobal().info(relicOut);
        Logger.getGlobal().info(potionOut);
    }

    private static String fillCardData() {
        cardData = new HashMap<>();
        List<InfoCard> cards = bundles.findAllCards();
        cards.forEach(data -> cardData.compute(data.getCard_id(), (k, v) -> (v!=null) ? mostRecent(cardData.get(k), data) : data));
        return "Card Info DB: " + cardData.size();
    }

    private static String fillRelicData() {
        relicData = new HashMap<>();
        List<InfoRelic> relics = bundles.findAllRelics();
        relics.forEach(data -> relicData.compute(data.getRelic_id(), (k, v) -> (v!=null) ? mostRecent(relicData.get(k), data) : data));
        return"Relic Info DB: " + relicData.size();
    }

    private static String fillPotionData() {
        potionData = new HashMap<>();
        List<InfoPotion> potions = bundles.findAllPotions();
        potions.forEach(data -> potionData.compute(data.getPotion_id(), (k, v) -> (v!=null) ? mostRecent(potionData.get(k), data) : data));
        return "Potion Info DB: " + potionData.size();
    }

    private static InfoCard mostRecent(InfoCard inMap, InfoCard newEntry) {
        return (new Semver(newEntry.getInfo().getVersion()).isGreaterThan(new Semver(inMap.getInfo().getVersion()))) ? newEntry : inMap;
    }

    private static InfoPotion mostRecent(InfoPotion inMap, InfoPotion newEntry) {
        return (new Semver(newEntry.getInfo().getVersion()).isGreaterThan(new Semver(inMap.getInfo().getVersion()))) ? newEntry : inMap;
    }

    private static InfoRelic mostRecent(InfoRelic inMap, InfoRelic newEntry) {
        return (new Semver(newEntry.getInfo().getVersion()).isGreaterThan(new Semver(inMap.getInfo().getVersion()))) ? newEntry : inMap;
    }
}
