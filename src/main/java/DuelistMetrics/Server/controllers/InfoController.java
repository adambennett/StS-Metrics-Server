package DuelistMetrics.Server.controllers;


import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.services.*;
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

    @GetMapping("allModuleVersions")
    public ResponseEntity<?> getTrackedVersions() {
        List<String> versions = bundles.getAllModuleVersions();
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    @PostMapping("/dataupload")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> infoUpload(@RequestBody TopInfoBundle list){
        if (list != null) {
            List<ModInfoBundle> saved = new ArrayList<>(list.getInfo().size());
            for (ModInfoBundle mod : list.getInfo()) {
                bundles.createBundle(mod);
                saved.add(mod);
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
        cards.forEach(data -> cardData.put(data.getCard_id(), data));
        return "Card Info DB: " + cardData.size();
    }

    private static String fillRelicData() {
        relicData = new HashMap<>();
        List<InfoRelic> relics = bundles.findAllRelics();
        relics.forEach(data -> relicData.put(data.getRelic_id(), data));
        return"Relic Info DB: " + relicData.size();
    }

    private static String fillPotionData() {
        potionData = new HashMap<>();
        List<InfoPotion> potions = bundles.findAllPotions();
        potions.forEach(data -> potionData.put(data.getPotion_id(), data));
        return "Potion Info DB: " + potionData.size();
    }
}
