package DuelistMetrics.Server.controllers;


import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.models.dto.LookupPotion;
import DuelistMetrics.Server.models.dto.LookupRelic;
import DuelistMetrics.Server.models.enums.ScoringRunLookupType;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.models.tierScore.*;
import DuelistMetrics.Server.services.*;
import DuelistMetrics.Server.util.*;
import com.vdurmont.semver4j.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.logging.*;

@RestController
public class InfoController {

    private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.InfoController");

    private static InfoService bundles;
    private static BundleService bundleService;


    @Autowired
    public InfoController(InfoService service, BundleService bnd) {
        bundles = service;
        bundleService = bnd;
    }

    public static InfoService getService() { return bundles; }

    @GetMapping("/isAlive")
    public HttpStatus isAlive() {
        return HttpStatus.OK;
    }



    @GetMapping("/allTrackedDuelistVersions")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> allTrackedDuelistVersions() {
        return new ResponseEntity<>(bundles.getAllTrackedDuelistVersions(), HttpStatus.OK);
    }

    @GetMapping("/orbInfo")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> orbInfo() {
        return new ResponseEntity<>(bundles.getOrbInfo(), HttpStatus.OK);
    }

    @GetMapping("/cardLookup/{card}")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> cardLookup(@PathVariable String card) {
        if (card == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        boolean duelist = card.startsWith("theDuelist:");
        int magic = 17;
        String parsedCard = card;
        if (parsedCard.contains("+")) {
            int indexOfPlus = parsedCard.indexOf("+");
            parsedCard = parsedCard.substring(0, indexOfPlus);
        }
        List<String> toParse = bundles.getCardDataFromId(card, duelist);
        List<String> toParseFromParsed = bundles.getCardDataFromId(parsedCard, duelist);
        if (toParse == null || (toParseFromParsed != null && toParseFromParsed.size() > toParse.size())) {
            toParse = toParseFromParsed;
            card = parsedCard;
        }

        if (toParse == null || toParse.size() < 1) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<String> modData = bundles.getModDataFromId(card, duelist);
        List<String> cardProps;
        try {
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
                lookupCard.setModule(modData.get(0));
                lookupCard.setAuthors(modData.get(1));
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
        } catch (Exception ex) {
            logger.info("Exception during card lookup\n" + ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cardProps, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/relicLookup/{relic}")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<LookupRelic> relicLookup(@PathVariable String relic) {
        try {
            return new ResponseEntity<>(bundles.getRelicDataFromId(relic, relic.startsWith("theDuelist:")), HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Error looking up relic by ID - " + relic + "\n" + ExceptionUtils.getStackTrace(ex));
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/potionLookup/{potion}")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<LookupPotion> potionLookup(@PathVariable String potion) {
        try {
            return new ResponseEntity<>(bundles.getPotionDataFromId(potion, potion.startsWith("theDuelist:")), HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Error looking up potion by ID - " + potion + "\n" + ExceptionUtils.getStackTrace(ex));
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/dataupload")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> infoUpload(@RequestBody TopInfoBundle list){
        try {
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
                return new ResponseEntity<>(saved, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.info("Exception saving uploaded module info\n" + ExceptionUtils.getStackTrace(ex));
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/orbInfoUpload")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> infoUpload(@RequestBody List<DuelistOrbInfo> list){
        try {
            if (list != null) {
                for (DuelistOrbInfo orb : list) {
                    try {
                        bundles.createOrbInfo(orb);
                    } catch (SQLIntegrityConstraintViolationException | DataIntegrityViolationException ignored) {}
                    catch (Exception ex) {
                        logger.info("Exception saving duelist orb info\n" + ExceptionUtils.getStackTrace(ex));
                    }
                }
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception ignored) {}
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/allModuleVersions")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> getTrackedVersions() {
        List<String> versions = bundles.getAllModuleVersions();
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    @GetMapping("/anubisScoreAverage")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
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
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
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

    @GetMapping("/modlist-v2")
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> getAllModsNew() {
        return new ResponseEntity<>(bundles.getModListNew(), HttpStatus.OK);
    }

    @GetMapping(value={"/tierScores", "/tierScores/{pool}", "/tierScores/{pool}/{cardId}"})
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> checkTierScores(@PathVariable(required = false) String pool, @PathVariable(required = false) String cardId) {
        if (pool == null) {
            return new ResponseEntity<>(bundles.getAllTierScores(), HttpStatus.OK);
        }
        String cardFilter = cardId != null && !cardId.equals("any") ? cardId : null;
        if (cardFilter == null) {
            String poolName;
            if (pool.contains("Random")) {
                poolName = pool.contains("Small") ? "Random Pool (Small)" : "Random Pool (Big)";
            } else {
                poolName = pool + " Pool";
            }
            return new ResponseEntity<>(bundles.getTierScores(poolName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bundles.getTierScores(cardFilter, pool + " Pool"), HttpStatus.OK);
        }
    }


    @GetMapping(value={"/calculateTierScores", "/calculateTierScores/{ascension}", "/calculateTierScores/{ascension}/{challenge}", "/calculateTierScores/{ascension}/{challenge}/{deckName}", "/calculateTierScoresTyped/{type}", "/calculateTierScoresTyped/{type}/{ascension}", "/calculateTierScoresTyped/{type}/{ascension}/{challenge}", "/calculateTierScoresTyped/{type}/{ascension}/{challenge}/{deckName}"})
    @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
    public ResponseEntity<?> getTierScores(@PathVariable(required = false) String challenge, @PathVariable(required = false)  String ascension, @PathVariable(required = false) String deckName, @PathVariable(required = false) String type) {
        try {
            // Handle path variables
            int ascensionFilter = -1;
            int challengeFilter = -2;
            String deckFilter;
            try { if (ascension != null) { ascensionFilter = Integer.parseInt(ascension); }} catch (Exception ignored) {}
            try { if (challenge != null) { challengeFilter = Integer.parseInt(challenge); }} catch (Exception ignored) {}
            boolean filteringDeckName  = deckName != null && !deckName.equalsIgnoreCase("any");
            deckFilter = filteringDeckName ? deckName : null;

            ScoringRunLookupType lookupType = ScoringRunLookupType.LEGACY;
            if (type != null) {
                for (ScoringRunLookupType lookup : ScoringRunLookupType.values()) {
                    if (lookup.toString().equalsIgnoreCase(type)) {
                        lookupType = lookup;
                        break;
                    }
                }
            }

            // Calculate scores
            Map<String, List<ScoredCard>> scoredOutput = calculateTierScores(challengeFilter, ascensionFilter, deckFilter, lookupType);

            // Format for readable response JSON
            Map<String,  List<MinimalScoredCard>> justScores = new HashMap<>();
            for (Map.Entry<String, List<ScoredCard>> entry : scoredOutput.entrySet()) {
                String pool = entry.getKey();
                justScores.put(pool, new ArrayList<>());
                List<MinimalScoredCard> list = justScores.get(pool);
                for (ScoredCard card : entry.getValue()) {
                    MinimalScoredCard msc = new MinimalScoredCard(card.card_id);
                    msc.overall_score = card.getOverall_score();
                    msc.act0_score = card.getAct0_score();
                    msc.act1_score = card.getAct1_score();
                    msc.act2_score = card.getAct2_score();
                    msc.act3_score = card.getAct3_score();
                    list.add(msc);
                }
                Collections.sort(list);
            }

            return new ResponseEntity<>(justScores, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    public static void saveTierScores(ScoredCard card) {
        bundles.createTierScore(card);
    }

    public static void saveTierScores(ScoredCardV4 card) {
        bundles.createTierScore(card);
    }

    public static void saveTierScores(ScoredCardA20 card) {
        bundles.createTierScore(card);
    }

    public static Map<String,List<String>> getLegacyTrackedCardsForTierScores(String poolName) {
        Map<String,List<String>> data = bundles.getLegacyTrackedCardsForTierScores(poolName);
        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            String pool = entry.getKey();
            if (pool.contains("[Basic/Colorless]")) {
                String[] splice = pool.split("\\[");
                String basePool = splice[0].trim();
                if (data.containsKey(basePool)) {
                    List<String> cards = data.get(basePool);
                    cards.addAll(entry.getValue());
                    keysToRemove.add(pool);
                }
            }
        }
        for (String key : keysToRemove) {
            data.remove(key);
        }
        return data;
    }

    public static Map<String,List<String>> getV4TrackedCardsForTierScores(String poolName) {
        Map<String,List<String>> data = bundles.getV4TrackedCardsForTierScores(poolName);
        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            String pool = entry.getKey();
            if (pool.contains("[Basic/Colorless]")) {
                String[] splice = pool.split("\\[");
                String basePool = splice[0].trim();
                if (data.containsKey(basePool)) {
                    List<String> cards = data.get(basePool);
                    cards.addAll(entry.getValue());
                    keysToRemove.add(pool);
                }
            }
        }
        for (String key : keysToRemove) {
            data.remove(key);
        }
        return data;
    }

    public static Map<String,List<String>> getA20TrackedCardsForTierScores(String poolName) {
        Map<String,List<String>> data = bundles.getA20TrackedCardsForTierScores(poolName);
        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            String pool = entry.getKey();
            if (pool.contains("[Basic/Colorless]")) {
                String[] splice = pool.split("\\[");
                String basePool = splice[0].trim();
                if (data.containsKey(basePool)) {
                    List<String> cards = data.get(basePool);
                    cards.addAll(entry.getValue());
                    keysToRemove.add(pool);
                }
            }
        }
        for (String key : keysToRemove) {
            data.remove(key);
        }
        return data;
    }

    public static <T extends GeneralScoringCard> Map<String, List<T>> calculateTierScores(int challengeThreshold, int ascensionThreshold, String deckName, ScoringRunLookupType type) {

        /* Process parameters                                                                                         */
        Integer ascensionFilter = ascensionThreshold;
        Integer challengeFilter = challengeThreshold;
        String deckFilter;
        boolean filteringAscension = ascensionFilter > -1;
        boolean filteringChallenge = challengeFilter > -2;
        boolean filteringDeckName  = deckName != null && !deckName.equals("any");
        ascensionFilter = filteringAscension ? ascensionFilter : null;
        challengeFilter = filteringChallenge ? challengeFilter : null;
        deckFilter = filteringDeckName ? deckName : null;

        /* Setup variables                                                                                            */
        // scored output
        //  (Pool)
        Map<String, List<T>> output = new HashMap<>();

        // Holds data used to construct actual output
        //  (Pool)     (CardId)    (Floor)
        Map<String, Map<String, Map<Integer, TierDataHolder>>> dataMap = new HashMap<>();

        // this is formatted output
        //  (Deck)
        Map<String, List<TierDataHolder>> destructuredDataMap = new HashMap<>();

        //  (Pool)     (CardId)
        Map<String, Map<String, PopsCard>> cardPopularity = new HashMap<>();
        Map<String, Map<String, PopsCard>> a0_cardPopularity = new HashMap<>();
        Map<String, Map<String, PopsCard>> a1_cardPopularity = new HashMap<>();
        Map<String, Map<String, PopsCard>> a2_cardPopularity = new HashMap<>();
        Map<String, Map<String, PopsCard>> a3_cardPopularity = new HashMap<>();
        Map<String, List<PopsCard>> sortedPopularity = new HashMap<>();
        Map<String, List<PopsCard>> a0_sortedPopularity = new HashMap<>();
        Map<String, List<PopsCard>> a1_sortedPopularity = new HashMap<>();
        Map<String, List<PopsCard>> a2_sortedPopularity = new HashMap<>();
        Map<String, List<PopsCard>> a3_sortedPopularity = new HashMap<>();

        // Pool Total Win rates
        //  (Pool)
        Map<String, PoolTotals> poolTotalsMap = new HashMap<>();

        /* Fetch list of cards we are tracking for tier scoring                                                       */
        // holds reference to which cards we care about scoring
        //  (Pool)      (CardId)
        Map<String, List<String>> cardsMap = switch(type) {
            case LEGACY -> getLegacyTrackedCardsForTierScores(deckFilter);
            case V4 -> getV4TrackedCardsForTierScores(deckFilter);
            case A20 -> getA20TrackedCardsForTierScores(deckFilter);
        };

        // holds list of all deck names of decks we're tracking
        //  (Pool)
        List<String> startingDecks = new ArrayList<>();

        // Holds a conversion map to convert back and forth between ex: "Standard Deck" and "Standard Pool"
        //  (Deck)  (Pool)
        Map<String, String> deckToPoolConvert = new HashMap<>();

        // Holds a conversion map to convert back and forth between ex: "Dragon Pool" and "Dragon Deck"
        //  (Pool)  (Deck)
        Map<String, String> poolToDeckConvert = new HashMap<>();

        // Reformat strings in startingDecks list to match suffix found in bundle table ("Deck" instead of "Pool")
        for (String key : cardsMap.keySet()) {
            if (key.contains("Basic/Colorless")) {
                String[] basicSplice = key.split("\\[");
                key = basicSplice[0].trim();
            }
            String[] splice = key.split(" ");
            String deck;
            if (key.startsWith("Ascended") || key.startsWith("Pharaoh")) {
                deck = splice[0] + " " + splice[1];
            } else if (key.startsWith("Random")) {
                if (key.contains("Small")) {
                    deck = "Random Deck (Small)";
                } else {
                    deck = "Random Deck (Big)";
                }
            } else {
                deck = splice[0] + " Deck";
            }
            if (!startingDecks.contains(deck)) {
                startingDecks.add(deck);
                deckToPoolConvert.put(deck, key);
                poolToDeckConvert.put(key, deck);
                Map<String, PopsCard> innerPops = new HashMap<>();
                Map<String, PopsCard> a0_innerPops = new HashMap<>();
                Map<String, PopsCard> a1_innerPops = new HashMap<>();
                Map<String, PopsCard> a2_innerPops = new HashMap<>();
                Map<String, PopsCard> a3_innerPops = new HashMap<>();
                List<PopsCard> innerPopsList = new ArrayList<>();
                List<PopsCard> a0_innerPopsList = new ArrayList<>();
                List<PopsCard> a1_innerPopsList = new ArrayList<>();
                List<PopsCard> a2_innerPopsList = new ArrayList<>();
                List<PopsCard> a3_innerPopsList = new ArrayList<>();
                dataMap.put(deck, new HashMap<>());
                output.put(key, new ArrayList<>());
                sortedPopularity.put(deck, innerPopsList);
                a0_sortedPopularity.put(deck, a0_innerPopsList);
                a1_sortedPopularity.put(deck, a1_innerPopsList);
                a2_sortedPopularity.put(deck, a2_innerPopsList);
                a3_sortedPopularity.put(deck, a3_innerPopsList);
                cardPopularity.put(key, innerPops);
                a0_cardPopularity.put(key, a0_innerPops);
                a1_cardPopularity.put(key, a1_innerPops);
                a2_cardPopularity.put(key, a2_innerPops);
                a3_cardPopularity.put(key, a3_innerPops);
                PoolTotals totals = new PoolTotals(key);
                totals.deck = deck;
                poolTotalsMap.put(key, totals);
            }
        }

        /* Fetch all runs needed for scoring                                                                          */
        // sort runs by starting deck and filter runs we dont care about for scoring
        //  (Pool)
        Map<String, List<TierBundle>> bundlesMap = switch(type) {
            case LEGACY -> bundleService.getLegacyBundlesForTierScores(startingDecks, ascensionFilter, challengeFilter);
            case V4 -> bundleService.getV4BundlesForTierScores(startingDecks, ascensionFilter, challengeFilter);
            case A20 -> bundleService.getA20BundlesForTierScores(startingDecks, ascensionFilter, challengeFilter);
        };

        /* Begin processing and scoring                                                                               */
        // Use data to construct win rates for all tracked cards (by deck)
        for (Map.Entry<String, List<TierBundle>> entry : bundlesMap.entrySet()) {

            // Setup 'global' variables
            // Deck name (ex: Standard Deck)
            String deck = entry.getKey();

            // Pool name (ex: Standard Pool)
            String pool = deckToPoolConvert.getOrDefault(deck, "Unknown");

            // Data holder for card popularity inside pool
            // (CardId)
            Map<String, PopsCard> popsCardMap = cardPopularity.get(pool);
            Map<String, PopsCard> a0_popsCardMap = a0_cardPopularity.get(pool);
            Map<String, PopsCard> a1_popsCardMap = a1_cardPopularity.get(pool);
            Map<String, PopsCard> a2_popsCardMap = a2_cardPopularity.get(pool);
            Map<String, PopsCard> a3_popsCardMap = a3_cardPopularity.get(pool);


            // Reference to inner map of global output data map for ease of updating
            // (CardId)     (Floor)
            Map<String, Map<Integer, TierDataHolder>> innerDataMap = dataMap.get(deck);

            // Skip if pool isn't found in tracked list (somehow)
            if (!pool.equals("Unknown")) {

                // List of cards from the current pool that we are interested in scoring
                List<String> trackedPoolCards = cardsMap.getOrDefault(pool, new ArrayList<>());
                if (trackedPoolCards.size() > 0) {
                    // For each run (Bundle)
                    for (TierBundle bundle : entry.getValue()) {

                        // For each floor that a card was picked during the run
                        for (Map.Entry<Integer, List<String>> cardPicked : bundle.card_choices.entrySet()) {

                            // For each card picked on the current floor
                            for (String card_id : cardPicked.getValue()) {

                                // Determine if picked in Act 0/1/2/3 based on floor card was picked on
                                int act = SpireUtils.getActFromFloor(cardPicked.getKey());
                                Map<Integer, TierDataHolder> actMap = innerDataMap.getOrDefault(card_id, new HashMap<>());

                                // If this card specifically is being tracked for this pool
                                if (trackedPoolCards.contains(card_id)) {

                                    // Setup tier data storage for this card
                                    TierDataHolder localDataHolder;

                                    // If we are already tracking this card in this pool
                                    if (innerDataMap.containsKey(card_id)) {
                                        if (!actMap.containsKey(act)) {
                                            actMap.put(act, new TierDataHolder(card_id, act));
                                        }
                                        localDataHolder = actMap.get(act);

                                        // Don't need to check pops maps for contains
                                        // because they follow the same flow as the innerDataMap here
                                        PopsCard toUpdate = popsCardMap.get(card_id);
                                        toUpdate.numberOfPicks++;
                                        switch (act) {
                                            case 0 -> {
                                                if (!a0_popsCardMap.containsKey(card_id)) {
                                                    a0_popsCardMap.put(card_id, new PopsCard(card_id));
                                                }
                                                PopsCard a0_toUpdate = a0_popsCardMap.get(card_id);
                                                a0_toUpdate.numberOfPicks++;
                                            }
                                            case 1 -> {
                                                if (!a1_popsCardMap.containsKey(card_id)) {
                                                    a1_popsCardMap.put(card_id, new PopsCard(card_id));
                                                }
                                                PopsCard a1_toUpdate = a1_popsCardMap.get(card_id);
                                                a1_toUpdate.numberOfPicks++;
                                            }
                                            case 2 -> {
                                                if (!a2_popsCardMap.containsKey(card_id)) {
                                                    a2_popsCardMap.put(card_id, new PopsCard(card_id));
                                                }
                                                PopsCard a2_toUpdate = a2_popsCardMap.get(card_id);
                                                a2_toUpdate.numberOfPicks++;
                                            }
                                            case 3 -> {
                                                if (!a3_popsCardMap.containsKey(card_id)) {
                                                    a3_popsCardMap.put(card_id, new PopsCard(card_id));
                                                }
                                                PopsCard a3_toUpdate = a3_popsCardMap.get(card_id);
                                                a3_toUpdate.numberOfPicks++;
                                            }
                                        }
                                    }

                                    // This card is a new addition to this pool
                                    else {
                                        innerDataMap.put(card_id, new HashMap<>());
                                        actMap.put(act, new TierDataHolder(card_id, act));
                                        localDataHolder = actMap.get(act);
                                        PopsCard toCreate = new PopsCard(card_id);
                                        toCreate.numberOfPicks++;
                                        popsCardMap.put(card_id, toCreate);
                                        switch (act) {
                                            case 0 -> {
                                                PopsCard a0_toCreate = new PopsCard(card_id);
                                                a0_toCreate.numberOfPicks++;
                                                a0_popsCardMap.put(card_id, a0_toCreate);
                                            }
                                            case 1 -> {
                                                PopsCard a1_toCreate = new PopsCard(card_id);
                                                a1_toCreate.numberOfPicks++;
                                                a1_popsCardMap.put(card_id, a1_toCreate);
                                            }
                                            case 2 -> {
                                                PopsCard a2_toCreate = new PopsCard(card_id);
                                                a2_toCreate.numberOfPicks++;
                                                a2_popsCardMap.put(card_id, a2_toCreate);
                                            }
                                            case 3 -> {
                                                PopsCard a3_toCreate = new PopsCard(card_id);
                                                a3_toCreate.numberOfPicks++;
                                                a3_popsCardMap.put(card_id, a3_toCreate);
                                            }
                                        }
                                    }

                                    // If the tier data has been prepared properly
                                    if (localDataHolder != null) {

                                        // Increase wins or losses of this card globally within the pool
                                        if (bundle.victory) {
                                            localDataHolder.wins++;
                                        } else {
                                            localDataHolder.losses++;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }

        // Calculate card popularity data as a percentile (overall)
        ScoringUtils.sortAndCalculatePercentiles(cardPopularity, sortedPopularity, poolToDeckConvert);

        // Calculate card popularity data as a percentile (act0)
        ScoringUtils.sortAndCalculatePercentiles(a0_cardPopularity, a0_sortedPopularity, poolToDeckConvert);

        // Calculate card popularity data as a percentile (act1)
        ScoringUtils.sortAndCalculatePercentiles(a1_cardPopularity, a1_sortedPopularity, poolToDeckConvert);

        // Calculate card popularity data as a percentile (act2)
        ScoringUtils.sortAndCalculatePercentiles(a2_cardPopularity, a2_sortedPopularity, poolToDeckConvert);

        // Calculate card popularity data as a percentile (act3)
        ScoringUtils.sortAndCalculatePercentiles(a3_cardPopularity, a3_sortedPopularity, poolToDeckConvert);

        /* Destructuring                                                                                              */
        // Destructure dataMap slightly to prepare for output (remove floor data, it's inside TierDataHolders now)
        for (Map.Entry<String, Map<String, Map<Integer, TierDataHolder>>> entry : dataMap.entrySet()) {
            String pool = entry.getKey();
            for (Map.Entry<String, Map<Integer, TierDataHolder>> innerEntry : entry.getValue().entrySet()) {
                for (Map.Entry<Integer, TierDataHolder> innerInnerEntry : innerEntry.getValue().entrySet()) {
                    TierDataHolder tierBundle = innerInnerEntry.getValue();
                    if (!destructuredDataMap.containsKey(pool)) {
                        destructuredDataMap.put(pool, new ArrayList<>());
                    }
                    destructuredDataMap.get(pool).add(tierBundle);
                }
            }
        }

        // Further destructuring - convert prepared output into Map<Pool, List<ScoredCard>>
        for (Map.Entry<String, List<TierDataHolder>> entry : destructuredDataMap.entrySet()) {
            String deck = entry.getKey();
            String pool = deckToPoolConvert.get(deck);
            Map<String, T> toAddToCards = new HashMap<>();
            for (TierDataHolder data : entry.getValue()) {
                GeneralScoringCard card = switch (type) {
                    case V4 -> new ScoredCardV4(data.cardId, pool);
                    case A20 -> new ScoredCardA20(data.cardId, pool);
                    default -> new ScoredCard(data.cardId, pool);
                };
                switch (data.act) {
                    case 0 -> {
                        card.setAct0_losses(card.getAct0_losses() + data.losses);
                        card.setAct0_wins(card.getAct0_wins() + data.wins);
                    }
                    case 1 -> {
                        card.setAct1_losses(card.getAct1_losses() + data.losses);
                        card.setAct1_wins(card.getAct1_wins() + data.wins);
                    }
                    case 2 -> {
                        card.setAct2_losses(card.getAct2_losses() + data.losses);
                        card.setAct2_wins(card.getAct2_wins() + data.wins);
                    }
                    case 3 -> {
                        card.setAct3_losses(card.getAct3_losses() + data.losses);
                        card.setAct3_wins(card.getAct3_wins() + data.wins);
                    }
                }
                toAddToCards.put(data.cardId, (T) card);
            }
            List<T> cards = new ArrayList<>(toAddToCards.values());
            if (!output.containsKey(pool)) {
                output.put(pool, cards);
            } else {
                output.get(pool).addAll(cards);
            }
        }

        /* Scoring                                                                                                    */
        // Calculate actual win rate for individual cards within each pool
        for (Map.Entry<String, List<T>> entry : output.entrySet()) {

            // Map of total win rates across all cards within a pool during different acts (a whole act win-rate column)
            PoolTotals pool = poolTotalsMap.get(entry.getKey());
            for (T card : entry.getValue()) {
                float a0_winrate = card.getAct0_losses() > 0 ? (float)card.getAct0_wins() / (card.getAct0_wins() + card.getAct0_losses()) : 0.0f;
                float a1_winrate = card.getAct1_losses() > 0 ? (float)card.getAct1_wins() / (card.getAct1_wins() + card.getAct1_losses()) : 0.0f;
                float a2_winrate = card.getAct2_losses() > 0 ? (float)card.getAct2_wins() / (card.getAct2_wins() + card.getAct2_losses()) : 0.0f;
                float a3_winrate = card.getAct3_losses() > 0 ? (float)card.getAct3_wins() / (card.getAct3_wins() + card.getAct3_losses()) : 0.0f;

                card.setAct0_win_rate(a0_winrate);
                card.setAct1_win_rate(a1_winrate);
                card.setAct2_win_rate(a2_winrate);
                card.setAct3_win_rate(a3_winrate);

                pool.act0 += a0_winrate;
                pool.act1 += a1_winrate;
                pool.act2 += a2_winrate;
                pool.act3 += a3_winrate;
                pool.cards++;
            }
        }

        // Final Scoring
        for (Map.Entry<String, List<T>> entry : output.entrySet()) {
            PoolTotals pool = poolTotalsMap.get(entry.getKey());
            String poolName = entry.getKey();
            for (T card : entry.getValue()) {
                PopsCard popData = cardPopularity.get(poolName).get(card.getCard_id());
                PopsCard a0_popData = a0_cardPopularity.get(poolName).getOrDefault(card.getCard_id(), new PopsCard(card.getCard_id()));
                PopsCard a1_popData = a1_cardPopularity.get(poolName).getOrDefault(card.getCard_id(), new PopsCard(card.getCard_id()));
                PopsCard a2_popData = a2_cardPopularity.get(poolName).getOrDefault(card.getCard_id(), new PopsCard(card.getCard_id()));
                PopsCard a3_popData = a3_cardPopularity.get(poolName).getOrDefault(card.getCard_id(), new PopsCard(card.getCard_id()));

                // Delta formula:
                //  [act win rate] - [average act win rate for pool]
                card.setAct0_delta(card.getAct0_win_rate() - (pool.act0 / pool.cards));
                card.setAct1_delta(card.getAct1_win_rate() - (pool.act1 / pool.cards));
                card.setAct2_delta(card.getAct2_win_rate() - (pool.act2 / pool.cards));
                card.setAct3_delta(card.getAct3_win_rate() - (pool.act3 / pool.cards));

                // Score formula:
                //  [act delta * 1000] + 50
                card.setAct0_score((int)((card.getAct0_delta() * 1000) + 50));
                card.setAct1_score((int)((card.getAct1_delta() * 1000) + 50));
                card.setAct2_score((int)((card.getAct2_delta() * 1000) + 50));
                card.setAct3_score((int)((card.getAct3_delta() * 1000) + 50));

                // Weight scores based on act and sum to create initial overall score
                card.setOverall_score((int) ((card.getAct0_score() * 0.15) + (card.getAct1_score() * 1.5) + (card.getAct2_score()) + (card.getAct3_score() * 0.5)));

                // Throttle positive scores with low sample size (overall)
                if (!popData.aboveFortyPercentile && card.getOverall_score() > 0) {
                    card.multOverall(popData.percentile);
                }
                card.setPercentile(popData.percentile);
                card.setPosition(popData.position);

                // Throttle positive scores with low sample size (act0)
                if (!a0_popData.aboveFortyPercentile && card.getAct0_score() > 0) {
                    card.multA0(a0_popData.percentile);
                }
                card.setA0_percentile(a0_popData.percentile);
                card.setA0_position(a0_popData.position);

                // Throttle positive scores with low sample size (act1)
                if (!a1_popData.aboveFortyPercentile && card.getAct1_score() > 0) {
                    card.multA1(a1_popData.percentile);
                }
                card.setA1_percentile(a1_popData.percentile);
                card.setA1_position(a1_popData.position);

                // Throttle positive scores with low sample size (act2)
                if (!a2_popData.aboveFortyPercentile && card.getAct2_score() > 0) {
                    card.multA2(a2_popData.percentile);
                }
                card.setA2_percentile(a2_popData.percentile);
                card.setA2_position(a2_popData.position);

                // Throttle positive scores with low sample size (act3)
                if (!a3_popData.aboveFortyPercentile && card.getAct3_score() > 0) {
                    card.multA3(a3_popData.percentile);
                }
                card.setA3_percentile(a3_popData.percentile);
                card.setA3_position(a3_popData.position);
            }

            // Calculate highest and lowest score of entire pool - overall and for each act
            int highestScore = -1; int highestA0Score = -1; int highestA1Score = -1; int highestA2Score = -1; int highestA3Score = -1;
            int lowestA0Score = 1; int lowestA1Score = 1; int lowestA2Score = 1; int lowestA3Score = 1; int lowestNegative = 1;
            for (T card : entry.getValue()) {
                if (card.getOverall_score() > highestScore) {
                    highestScore = card.getOverall_score();
                }
                if (card.getAct0_score() > highestA1Score) {
                    highestA0Score = card.getAct0_score();
                }
                if (card.getAct1_score() > highestA1Score) {
                    highestA1Score = card.getAct1_score();
                }
                if (card.getAct2_score() > highestA2Score) {
                    highestA2Score = card.getAct2_score();
                }
                if (card.getAct3_score() > highestA3Score) {
                    highestA3Score = card.getAct3_score();
                }
                if (card.getOverall_score() < 0 && card.getOverall_score() < lowestNegative) {
                    lowestNegative = card.getOverall_score();
                }
                if (card.getAct0_score() < 0 && card.getAct0_score() < lowestA0Score) {
                    lowestA0Score = card.getAct0_score();
                }
                if (card.getAct1_score() < 0 && card.getAct1_score() < lowestA1Score) {
                    lowestA1Score = card.getAct1_score();
                }
                if (card.getAct2_score() < 0 && card.getAct2_score() < lowestA2Score) {
                    lowestA2Score = card.getAct2_score();
                }
                if (card.getAct3_score() < 0 && card.getAct3_score() < lowestA3Score) {
                    lowestA3Score = card.getAct3_score();
                }
            }

            // Adjust all scores to align with scale [-100 <---> 100]
            for (T card : entry.getValue()) {
                if (card.getOverall_score() >= 0) {
                    card.setOverall_score(Math.round((card.getOverall_score() / (float)highestScore) * 100));
                } else {
                    card.setOverall_score(Math.round((card.getOverall_score() / (float)-lowestNegative) * 100));
                }

                if (card.getAct0_score() >= 0) {
                    card.setAct0_score(Math.round((card.getAct0_score() / (float)highestA0Score) * 100));
                } else {
                    card.setAct0_score(Math.round((card.getAct0_score() / (float)-lowestA0Score) * 100));
                }

                if (card.getAct1_score() >= 0) {
                    card.setAct1_score(Math.round((card.getAct1_score() / (float)highestA1Score) * 100));
                } else {
                    card.setAct1_score(Math.round((card.getAct1_score() / (float)-lowestA1Score) * 100));
                }

                if (card.getAct2_score() >= 0) {
                    card.setAct2_score(Math.round((card.getAct2_score() / (float)highestA2Score) * 100));
                } else {
                    card.setAct2_score(Math.round((card.getAct2_score() / (float)-lowestA2Score) * 100));
                }

                if (card.getAct3_score() >= 0) {
                    card.setAct3_score(Math.round((card.getAct3_score() / (float)highestA3Score) * 100));
                } else {
                    card.setAct3_score(Math.round((card.getAct3_score() / (float)-lowestA3Score) * 100));
                }

                // Adjust all scores upwards by 100 to further define scale as [0 <---> 100]
                card.inc100();
            }
        }

        return output;
    }
}
