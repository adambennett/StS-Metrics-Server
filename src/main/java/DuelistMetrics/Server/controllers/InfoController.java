package DuelistMetrics.Server.controllers;


import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.services.*;
import com.vdurmont.semver4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.logging.*;

@RestController
public class InfoController {
    
    private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.InfoController");

    private static InfoService bundles;
    private static Map<String, InfoCard> cardData;
    private static Map<String, InfoRelic> relicData;
    private static Map<String, InfoPotion> potionData;
    private static Map<String, InfoCreature> creatureData;


    @Autowired
    public InfoController(InfoService service) {
        bundles = service;
        fillAllData();
    }

    public static InfoService getService() { return bundles; }

    @GetMapping("/cardLookup/{card}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> cardLookup(@PathVariable String card) {
        boolean duelist = card.startsWith("theDuelist:");
        int magic = 17;
        List<String> toParse = bundles.getCardDataFromId(card, duelist);
        List<String> cardProps;
        String[] splice = toParse.get(0).split(",");
        cardProps = new ArrayList<>(Arrays.asList(splice));
        if (duelist) {
            for (int i = 1; i < toParse.size(); i++) {
                cardProps.add(toParse.get(i));
            }
        }
        if (cardProps.size() > magic) {
            LookupCardBuilder lookupCard = new LookupCardBuilder()
                    .setBlock(Integer.parseInt(cardProps.get(0)))
                    .setCard_id(cardProps.get(1))
                    .setColor(cardProps.get(2))
                    .setCost(cardProps.get(3))
                    .setDamage(Integer.parseInt(cardProps.get(4)))
                    .setDuelistType(cardProps.get(5))
                    .setEntomb(Integer.parseInt(cardProps.get(6)))
                    .setIsDuelistCard(Boolean.parseBoolean(cardProps.get(7)))
                    .setMagicNumber(Integer.parseInt(cardProps.get(8)))
                    .setName(cardProps.get(9))
                    .setRarity(cardProps.get(10))
                    .setSecondMag(Integer.parseInt(cardProps.get(11)))
                    .setSummons(Integer.parseInt(cardProps.get(12)))
                    .setThirdMag(Integer.parseInt(cardProps.get(13)))
                    .setTributes(Integer.parseInt(cardProps.get(14)))
                    .setType(cardProps.get(15));
            try {
                lookupCard.setMaxUpgrades(Integer.parseInt(cardProps.get(16)));
            } catch (Exception ex) {
                lookupCard.setMaxUpgrades(-1);
                magic = cardProps.get(16).equals("null") ? 17 : 16;
            }
            StringBuilder allText = new StringBuilder();
            StringBuilder nlText = new StringBuilder();
            if (duelist) {
                List<String> pools = new ArrayList<>();
                int i = magic;
                for (;!cardProps.get(i).equals("TEXT"); i++) {
                    pools.add(cardProps.get(i));
                }
                i++;
                lookupCard.setPools(pools);

                for (;!cardProps.get(i).equals("NEWLINETEXT");i++) {
                    allText.append(cardProps.get(i));
                    if (i + 1 < cardProps.size()) {
                        allText.append(",");
                    }
                }
                i++;
                for (;i<cardProps.size();i++) {
                    nlText.append(cardProps.get(i));
                    if (i + 1 < cardProps.size()) {
                        nlText.append(",");
                    }
                }
            } else {
                for (int i = magic; i < cardProps.size(); i++) {
                    allText.append(cardProps.get(i));
                    if (i + 1 < cardProps.size()) {
                        allText.append(",");
                    }
                }
            }
            lookupCard.setText(allText.toString());
            lookupCard.setNewLineText(nlText.toString());
            LookupCard lookup = lookupCard.createLookupCard();
            return new ResponseEntity<>(lookup, HttpStatus.OK);
        } else if (cardProps.size() == magic) {
            LookupCardBuilder lookupCard = new LookupCardBuilder()
                    .setBlock(Integer.parseInt(cardProps.get(0)))
                    .setCard_id(cardProps.get(1))
                    .setColor(cardProps.get(2))
                    .setCost(cardProps.get(3))
                    .setDamage(Integer.parseInt(cardProps.get(4)))
                    .setDuelistType(cardProps.get(5))
                    .setEntomb(Integer.parseInt(cardProps.get(6)))
                    .setIsDuelistCard(Boolean.parseBoolean(cardProps.get(7)))
                    .setMagicNumber(Integer.parseInt(cardProps.get(8)))
                    .setName(cardProps.get(9))
                    .setRarity(cardProps.get(10))
                    .setSecondMag(Integer.parseInt(cardProps.get(11)))
                    .setSummons(Integer.parseInt(cardProps.get(12)))
                    .setThirdMag(Integer.parseInt(cardProps.get(13)))
                    .setTributes(Integer.parseInt(cardProps.get(14)))
                    .setType(cardProps.get(15))
                    .setText("No text found.");
            try {
                lookupCard.setMaxUpgrades(Integer.parseInt(cardProps.get(16)));
            } catch (Exception ex) {
                lookupCard.setMaxUpgrades(-1);
            }
            return new ResponseEntity<>(lookupCard.createLookupCard(), HttpStatus.OK);
        }
        return new ResponseEntity<>(cardProps, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/dataupload")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> infoUpload(@RequestBody TopInfoBundle list){
        if (list != null) {
            Map<String, List<String>> output = recheckModules();
            List<ModInfoBundle> saved = new ArrayList<>(list.getInfo().size());
            for (ModInfoBundle mod : list.getInfo()) {
                if (output.containsKey(mod.getModID())) {
                    if (!output.get(mod.getModID()).contains(mod.getVersion())) {
                        saved.add(bundles.createBundle(mod));
                    }
                } else {
                    saved.add(bundles.createBundle(mod));
                }
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

    @GetMapping("/anubisScoreAverage")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getAnubisScoreAverage() {
        String visits = bundles.getAnubisVisits();
        Integer totalVisits = 0;
        try {
            totalVisits = Integer.parseInt(visits);
        } catch (NumberFormatException ex) {
            logger.info("Issue parsing totalVisits! Attempted to parse string: " + visits);
        }
        Map<Integer, Double> anubisData = bundles.getAnubisData();
        Map<String, Number> response = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : anubisData.entrySet()) {
            response.put("scoredVisits", entry.getKey());
            response.put("totalVisits", totalVisits);
            response.put("averageScore", entry.getValue());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/modlist")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getAllMods() {
        List<String> versions = bundles.getModList();
        List<Country> mods = new ArrayList<>();
        for (String module : versions) {
            String[] splice = module.split(",");
            String name = splice.length > 1 ? splice[1] : "Slay the Spire";
            Country mod = new Country(name, splice[0]);
            mods.add(mod);
        }
        Collections.sort(mods);
        return new ResponseEntity<>(mods, HttpStatus.OK);
    }

    private static Map<String, List<String>> recheckModules() {
        List<String> out = bundles.getAllModuleVersions();
        Map<String, List<String>> output = new HashMap<>();
        for (String module : out) {
            String[] splice = module.split(",");
            String mod = splice[0];
            String ver = splice[1];
            List<String> newArr;
            if (output.containsKey(mod)) {
                newArr = output.get(mod);
            } else {
                newArr = new ArrayList<>();
            }
            newArr.add(ver);
            output.put(mod, newArr);
        }
        return output;
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

    public static Optional<InfoCreature> getCreature(String game_id) {
        if (creatureData.containsKey(game_id)) { return Optional.of(creatureData.get(game_id)); }
        return Optional.empty();
    }

    private static void fillAllData() {
        String cardOut = fillCardData();
        String relicOut = fillRelicData();
        String potionOut = fillPotionData();
        String creatureOut = fillCreatureData();
        logger.info(cardOut);
        logger.info(relicOut);
        logger.info(potionOut);
        logger.info(creatureOut);
    }

    private static String fillCardData() {
        cardData = new HashMap<>();
//        List<InfoCard> cards = bundles.findAllCards();
//        cards.forEach(data -> cardData.compute(data.getCard_id(), (k, v) -> (v!=null) ? mostRecent(cardData.get(k), data) : data));
        return "Card Info DB: " + cardData.size();
    }

    private static String fillRelicData() {
        relicData = new HashMap<>();
//        List<InfoRelic> relics = bundles.findAllRelics();
//        relics.forEach(data -> relicData.compute(data.getRelic_id(), (k, v) -> (v!=null) ? mostRecent(relicData.get(k), data) : data));
        return"Relic Info DB: " + relicData.size();
    }

    private static String fillPotionData() {
        potionData = new HashMap<>();
//        List<InfoPotion> potions = bundles.findAllPotions();
//        potions.forEach(data -> potionData.compute(data.getPotion_id(), (k, v) -> (v!=null) ? mostRecent(potionData.get(k), data) : data));
        return "Potion Info DB: " + potionData.size();
    }

    private static String fillCreatureData() {
        creatureData = new HashMap<>();
//        List<InfoCreature> potions = bundles.findAllCreatures();
//        potions.forEach(data -> creatureData.compute(data.getCreature_id(), (k, v) -> (v!=null) ? mostRecent(creatureData.get(k), data) : data));
        return "Creature Info DB: " + creatureData.size();
    }

    private static InfoCreature mostRecent(InfoCreature inMap, InfoCreature newEntry) {
        if (!inMap.getInfo().getModID().equals("slay-the-spire")) {
            try {
                Semver inSem = new Semver(inMap.getInfo().getVersion());
                Semver newSem = new Semver(newEntry.getInfo().getVersion());
                return newSem.isGreaterThan(inSem) ? newEntry : inMap;
            } catch (SemverException ignored) {}
        }
        return inMap;
    }

    private static InfoCard mostRecent(InfoCard inMap, InfoCard newEntry) {
        if (!inMap.getInfo().getModID().equals("slay-the-spire")) {
            try {
                Semver inSem = new Semver(inMap.getInfo().getVersion());
                Semver newSem = new Semver(newEntry.getInfo().getVersion());
                return newSem.isGreaterThan(inSem) ? newEntry : inMap;
            } catch (SemverException ignored) {}
        }
        return inMap;
    }

    private static InfoPotion mostRecent(InfoPotion inMap, InfoPotion newEntry) {
        if (!inMap.getInfo().getModID().equals("slay-the-spire")) {
            try {
                Semver inSem = new Semver(inMap.getInfo().getVersion());
                Semver newSem = new Semver(newEntry.getInfo().getVersion());
                return newSem.isGreaterThan(inSem) ? newEntry : inMap;
            } catch (SemverException ignored) {}
        }
        return inMap;
    }

    private static InfoRelic mostRecent(InfoRelic inMap, InfoRelic newEntry) {
        if (!inMap.getInfo().getModID().equals("slay-the-spire")) {
            try {
                Semver inSem = new Semver(inMap.getInfo().getVersion());
                Semver newSem = new Semver(newEntry.getInfo().getVersion());
                return newSem.isGreaterThan(inSem) ? newEntry : inMap;
            } catch (SemverException ignored) {}
        }
        return inMap;
    }
}
