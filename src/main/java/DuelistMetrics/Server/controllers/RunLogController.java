package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.models.runDetails.*;
import DuelistMetrics.Server.services.*;
import DuelistMetrics.Server.util.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.lang.*;
import java.lang.String;
import java.math.*;
import java.util.*;
import java.util.logging.*;

@RestController
public class RunLogController {

    private static final Logger logger = Logger.getLogger("DuelistMetrics.RunLogController");

    private static RunLogService bundles;
    private static BundleService realBundles; // temp delete this
    private static InfoService infos;         // this too

    @Autowired
    public RunLogController(RunLogService service, BundleService serv, InfoService inf) { bundles = service; realBundles = serv; infos = inf;}

    public static void updateModInfoBundles() {
        List<ModInfoBundle> mods = infos.getAllMods();
        for (ModInfoBundle mod : mods) {
            mod.setDisplayName(mod.getName());
            infos.updateQuickFields(mod);
        }
    }

    public static void updateAllRunLogsWithCountryAndTime() {
        Collection<RunLog> runs = bundles.findAll();
        for (RunLog log : runs) {
            if (log.getFilterDate() == null) {
                Optional<Bundle> bnd = realBundles.findByIdInner(log.getRun_id());
                if (bnd.isPresent()) {
                    Bundle bundle = bnd.get();
                    log.setCountry(bundle.getCountry());
                    log.setLanguage(bundle.getLang());
                    log.setFilterDate(bundle.getLocal_time());
                    bundles.create(log);
                }
            }
        }
    }

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

    private record RunDetailsID(String host, BigDecimal localTime) {}
    public record SimpleCardExtended(String name, String id, SimpleCardExtendedType type) {}
    public enum SimpleCardExtendedType { Card, Relic, Potion, Unknown }

    @PostMapping("/run/details")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getRunDetails(@RequestBody RunDetailsID runDetailsInfo) {
        var host = runDetailsInfo.host;
        var localTime = runDetailsInfo.localTime;
        var canLookup = host != null && !host.equals("") && localTime != null;
        if (canLookup) {
            return runDetailsLogic(realBundles.findByHostAndLocalTime(host, localTime));
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private static String roomKeyToRoom(String roomKey, int floor) {
        var defaultOutput = "Unknown [ " + roomKey + " ]";
        if (roomKey == null && floor == 0) return "Neow";
        else if (roomKey == null && floor == 1) return "Combat";
        else if (roomKey == null) return defaultOutput;
        return switch (roomKey) {
            case "R" -> "Campfire";
            case "M" -> "Combat";
            case "E" -> "Elite";
            case "T" -> "Chest";
            case "B" -> "Boss";
            case "$" -> "Shop";
            case "?" -> "Event";
            default -> defaultOutput;
        };
    }

    private static List<FloorInfo> getFloorInfo(RunDetails run, TopBundle top, Map<String, String> cardMap, Map<String, String> relicMap, Map<String, String> potionMap) {
        var output = new ArrayList<FloorInfo>();
        var damageMap = new HashMap<Integer, DamageInfo>();
        var itemMap = new HashMap<Integer, List<SimpleCardExtended>>();
        var cardsChosen = new HashMap<Integer, List<SimpleCardExtended>>();
        var relicsChosen = new HashMap<Integer, List<SimpleCardExtended>>();
        var potionsChosen = new HashMap<Integer, List<SimpleCardExtended>>();
        var skipped = new HashMap<Integer, List<SimpleCardExtended>>();
        var upgraded = new HashMap<Integer, List<SimpleCardExtended>>();
        var events = new HashMap<Integer, List<Object>>();
        var data = top.getEvent();
        for (var campfire : run.getTop().getEvent().getCampfire_choices()) {
            if (campfire.getKey().equals("SMITH")) {
                var floor = campfire.getFloor();
                var list = upgraded.getOrDefault(floor, new ArrayList<>());
                var id = campfire.getId();
                var card = SpireUtils.parseBaseIdToSimpleCard(id, cardMap, relicMap, potionMap);
                list.add(new SimpleCardExtended(card.card().name, card.card().id, SimpleCardExtendedType.Card));
                upgraded.put(floor, list);
            }
        }
        for (var damage : data.getDamage_taken()) {
            damageMap.put(damage.getFloor(), damage);
        }
        for (var i = 0; i < data.getItem_purchase_floors().size(); i++) {
            var floor = data.getItem_purchase_floors().get(i);
            var item = data.getItems_purchased().get(i);
            var fullItem = SpireUtils.parseBaseIdToSimpleCard(item, cardMap, relicMap, potionMap);
            var list = itemMap.getOrDefault(floor + 1, new ArrayList<>());
            list.add(new SimpleCardExtended(fullItem.name(), fullItem.card().id, fullItem.type()));
            itemMap.put(floor + 1, list);
        }
        for (var card : run.top.getEvent().getCard_choices()) {
            var floor = card.getFloor();
            var fullItem = SpireUtils.parseBaseIdToSimpleCard(card.getCardId(), cardMap, relicMap, potionMap);
            for (var notPicked : card.getNot_picked()) {
                var list = skipped.getOrDefault(floor, new ArrayList<>());
                var fullSkipItem = SpireUtils.parseBaseIdToSimpleCard(notPicked.id, cardMap, relicMap, potionMap);
                list.add(new SimpleCardExtended(fullSkipItem.name(), fullSkipItem.card().id, SimpleCardExtendedType.Card));
                skipped.put(floor, list);
            }
            if (!fullItem.name().equals("SKIP")) {
                var list = cardsChosen.getOrDefault(floor, new ArrayList<>());
                list.add(new SimpleCardExtended(fullItem.name(), fullItem.card().id, SimpleCardExtendedType.Card));
                cardsChosen.put(floor, list);
            }
        }
        for (var card : run.top.getEvent().getRelics_obtained()) {
            var floor = card.getFloor();
            var fullItem = SpireUtils.parseBaseIdToSimpleCard(card.getId(), cardMap, relicMap, potionMap);
            var list = cardsChosen.getOrDefault(floor, new ArrayList<>());
            list.add(new SimpleCardExtended(fullItem.name(), fullItem.card().id, SimpleCardExtendedType.Relic));
            relicsChosen.put(floor, list);
        }
        for (var card : run.top.getEvent().getPotions_obtained()) {
            var floor = card.getFloor();
            var fullItem = SpireUtils.parseBaseIdToSimpleCard(card.getId(), cardMap, relicMap, potionMap);
            var list = cardsChosen.getOrDefault(floor, new ArrayList<>());
            list.add(new SimpleCardExtended(fullItem.name(), fullItem.card().id, SimpleCardExtendedType.Potion));
            potionsChosen.put(floor, list);
        }
        for (var event : run.top.getEvent().event_choices) {
            var floor = event.getFloor();
            var list = events.getOrDefault(floor, new ArrayList<>());
            list.add(event);
            events.put(floor, list);
        }
        var roomsSize = data.getPath_per_floor().size();
        var lastGold = 0;
        for (var i = 0; i < data.getGold_per_floor().size(); i++) {

            var floorInfo = new FloorInfo();
            var floor = i - 1;
            var pathIndex = i - 2;
            var damageRecord = damageMap.getOrDefault(floor, null);

            floorInfo.floor = floor;
            floorInfo.roomKey = pathIndex < roomsSize && pathIndex > -1 ? data.getPath_per_floor().get(pathIndex) : null;
            floorInfo.roomKey = floorInfo.roomKey == null && i == 1 ? "M" : floorInfo.roomKey;
            floorInfo.actualRoom = roomKeyToRoom(floorInfo.roomKey, i);
            floorInfo.goldChange = data.getGold_per_floor().get(i) - lastGold;
            floorInfo.currentGold = data.getGold_per_floor().get(i);
            floorInfo.currentHP = data.getCurrent_hp_per_floor().get(i);
            floorInfo.maxHP = data.getMax_hp_per_floor().get(i);
            floorInfo.hp = floorInfo.currentHP + "/" + floorInfo.maxHP;
            floorInfo.turns = damageRecord != null ? damageRecord.getTurns() : null;
            floorInfo.damage = damageRecord != null ? damageRecord.getDamage() : null;
            floorInfo.encounter = damageRecord != null ? damageRecord.getEnemies() : null;
            floorInfo.purchased = new ArrayList<>(itemMap.getOrDefault(floor, new ArrayList<>()));
            floorInfo.skipped = new ArrayList<>(skipped.getOrDefault(floor, new ArrayList<>()));
            floorInfo.upgraded = new ArrayList<>(upgraded.getOrDefault(floor, new ArrayList<>()));
            floorInfo.obtained = new ArrayList<>(cardsChosen.getOrDefault(floor, new ArrayList<>()));
            floorInfo.obtained.addAll(potionsChosen.getOrDefault(floor, new ArrayList<>()));
            floorInfo.obtained.addAll(relicsChosen.getOrDefault(floor, new ArrayList<>()));
            floorInfo.events = new ArrayList<>(events.getOrDefault(floor, new ArrayList<>()));
            lastGold = data.getGold_per_floor().get(i);
            output.add(floorInfo);
        }
        return output;
    }

    private static ResponseEntity<?> runDetailsLogic(TopBundle top) {
        if (top != null) {
            RunDetails run = new RunDetails(new RunTop(top));
            List<Long> modsToCheck = new ArrayList<>();
            for (DetailsMiniMod mod : run.top.getEvent().getModList()) {
                modsToCheck.add(infos.getModInfoBundleFromMiniMod(mod.getModID(), mod.getModVersion()));
            }
            Map<String, String> cardMap = infos.cardIdMappingArchive(modsToCheck);
            Map<String, String> relicMap = infos.relicIdMappingArchive(modsToCheck);
            Map<String, String> potionMap = infos.potionIdMappingArchive(modsToCheck);

            // fill floors off top bundle
            // look for any same name events on same floor
            // collect all player choices into list of choices for those events
            // collect all events with name='Nameless Tomb'
            // starting points, magic score, rewards received + levels, spent points
            try {
                List<FloorInfo> floors = getFloorInfo(run, top, cardMap, relicMap, potionMap);
                run.setFloors(floors);
            } catch (Exception ex) {
                logger.info("Exception preparing floor info!" + ex);
            }

            for (int i = 0; i < run.top.getEvent().getMaster_deck().size(); i++) {
                String localId = run.top.getEvent().getMaster_deck().get(i).getId();
                String localName = SpireUtils.parseBaseId(localId, cardMap, relicMap, potionMap).name();
                run.top.getEvent().getMaster_deck().get(i).setName(localName);
            }
            run.top.getEvent().getItems_purchased().replaceAll(simpleCard -> SpireUtils.parseBaseIdToSimpleCard(simpleCard.getId(), cardMap, relicMap, potionMap).card());
            run.top.getEvent().getItems_purged().replaceAll(simpleCard -> SpireUtils.parseBaseIdToSimpleCard(simpleCard.getId(), cardMap, relicMap, potionMap).card());
            run.top.getEvent().getRelics().replaceAll(simpleCard -> SpireUtils.parseBaseIdToSimpleCard(simpleCard.getId(), cardMap, relicMap, potionMap).card());
            for (DetailsBossRelic relic : run.top.getEvent().getBoss_relics()) {
                relic.setRelicName(SpireUtils.parseBaseId(relic.getRelicId(), cardMap, relicMap, potionMap).name());
                relic.getNot_picked().replaceAll(simpleCard -> SpireUtils.parseBaseIdToSimpleCard(simpleCard.getId(), cardMap, relicMap, potionMap).card());
            }
            for (DetailsEvent event : run.top.getEvent().getEvent_choices()) {
                event.getCards_obtained().replaceAll(simpleCard -> SpireUtils.parseBaseIdToSimpleCard(simpleCard.getId(), cardMap, relicMap, potionMap).card());
                event.getRelics_obtained().replaceAll(simpleCard -> SpireUtils.parseBaseIdToSimpleCard(simpleCard.getId(), cardMap, relicMap, potionMap).card());
            }
            for (DetailsCard card : run.top.getEvent().getCard_choices()) {
                String cardId = card.getCardId();
                card.setCardName(SpireUtils.parseBaseId(cardId, cardMap, relicMap, potionMap).name());
                card.getNot_picked().replaceAll(simpleCard -> SpireUtils.parseBaseIdToSimpleCard(simpleCard.getId(), cardMap, relicMap, potionMap).card());
            }
            for (DetailsPotion potion : run.top.getEvent().getPotions_obtained()) {
                potion.setName(SpireUtils.parseBaseId(potion.getId(), cardMap, relicMap, potionMap).name());
            }
            for (DetailsCampfireChoice choice : run.top.getEvent().getCampfire_choices()) {
                if (choice.getId() != null) {
                    choice.setName(SpireUtils.parseBaseId(choice.getId(), cardMap, relicMap, potionMap).name());
                }
            }
            return new ResponseEntity<>(run, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/run/{id}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getRunDetails(@PathVariable Long id){
        return runDetailsLogic(realBundles.findById(id).orElse(null));
    }

    @PostMapping("/count-runs")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getBundles(@RequestBody RunCountParams params) {
        try {
            Long runs = bundles.countRuns(params);
            return new ResponseEntity<>(runs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/runs")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public Collection<RunLog> getBundlesNew(@RequestBody RunCountParams options) {
        try {
            return bundles.findAll(options.pageNumber, options.pageSize, options);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/runs/{character}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getBundles(@PathVariable String character){
        return bundles.getAllByChar(character);
    }

    @GetMapping("/runs-id/{ids}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getRunsById(@PathVariable String ids){
        String[] splice = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : splice) {
            try {
                Integer parsed = Integer.parseInt(id.trim());
                idList.add(parsed);
            } catch (Exception ignored) {}
        }
        if (idList.size() > 0) {
            long[] convert = new long[idList.size()];
            int counter = 0;
            for (int i : idList) {
                convert[counter] = i;
                counter++;
            }
            List<Long> convertList = new ArrayList<>();
            for (long l : convert) {
                convertList.add(l);
            }
            return new ArrayList<>(bundles.getAllById(convertList));
        }
        return new ArrayList<>();
    }

    @GetMapping("/runs-country/{country}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getRunsByCountry(@PathVariable String country){
        return bundles.getAllByCountry(country);
    }

    @GetMapping("/runs-host/{host}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getRunsByHost(@PathVariable String host){
        return bundles.getAllByHost(host);
    }

    @GetMapping("/runs-time/{time}/{time2}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getRunsByTime(@PathVariable String time, @PathVariable String time2){
        return bundles.getAllByTime(time, time2);
    }

    @GetMapping("/runs-nonduelist")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<RunLog> getAllNonDuelistCharacterRuns(){
        return bundles.getAllByAnyOtherChar("THE_DUELIST");
    }

    @GetMapping("/allCharacters")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static Collection<String> getCharacters() {
        List<String> out = bundles.getAllCharacters();
        Collections.sort(out);
        return out;
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
      List<String> highestChalId = getService().getHighestChallengeAllWithId();
      List<Integer> highestChalIdentifiers = new ArrayList<>();
      for (String id : highestChalId) {
          String[] splice = id.split(",");
          try {
              Integer parsed = Integer.parseInt(splice[0]);
              highestChalIdentifiers.add(parsed);
          } catch (Exception ignored) {
              System.out.println("Couldn't parse id: " + id);
          }
      }
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
                .setHighestChallengeRunID(highestChalIdentifiers)
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
      Map<String, List<Integer>> highestChalIds = getService().getHighestChallengeWithId();
      Map<String, String> deckToKilledBy = new HashMap<>();
      for (DeckKilledBy dkb : killed) {
        String creature = dkb.getKilled_by();
        deckToKilledBy.put(dkb.getDeck(), creature + " (" + dkb.getCount() + ")");
      }
      ArrayList<String> decks = new ArrayList<>();
      DisplayDeck nonDuelist = null;
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
          .setHighestChallengeRunID(highestChalIds.getOrDefault(deckName, new ArrayList<>()))
          .createDisplayDeck();
        if (deck.getC20runs() == null) { deck.setC20runs(0); }
        if (deck.getC20wins() == null) { deck.setC20wins(0); }
        if (deck.getKaiba() == null) { deck.setKaiba(0); }
        if (dName.equals("Non-Duelist Character")) {
            nonDuelist = deck;
        } else {
            output.add(deck);
        }
      }
      Collections.sort(output);
      output.add(0, allDeck);
      if (nonDuelist != null) { output.add(nonDuelist); }
      return output;
    }

    @GetMapping("/deckPopularity")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public static ResponseEntity<?> getDeckPopularity(){
        List<String> data = bundles.getDataForPopularity();
        Map<String, Integer> amts = new HashMap<>();
        Map<String, DeckPopularityBuilder> builders = new HashMap<>();
        List<DeckPopularity> out = new ArrayList<>();
        for (String s : data) {
            String[] splice = s.split(",");
            String date = splice[0];
            String year = date.substring(0, 4);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (Integer.parseInt(year) <= currentYear + 1) {
                String month = date.substring(4, 6);
                String deck = splice[1];
                String character = splice[2];
                String deckKey = deck + "-" + month + "-" + year;
                String charKey = character + "-" + month + "-" + year;
                if (!(deck.equals("NotYugi")) && amts.containsKey(deckKey)) {
                    amts.put(deckKey, amts.get(deckKey) + 1);
                    DeckPopularityBuilder builder = builders.get(deckKey);
                    builder.setAmount(builder.getAmount() + 1);
                } else if (amts.containsKey(charKey)) {
                    amts.put(charKey, amts.get(charKey) + 1);
                    DeckPopularityBuilder builder = builders.get(charKey);
                    builder.setAmount(builder.getAmount() + 1);
                } else {
                    if (deck.equals("NotYugi")) {
                        amts.put(charKey, 1);
                    } else {
                        amts.put(deckKey, 1);
                    }
                    DeckPopularityBuilder pop = new DeckPopularityBuilder()
                            .setCharacter(character)
                            .setDeck(deck)
                            .setMonth(Integer.parseInt(month))
                            .setYear(Integer.parseInt(year))
                            .setAmount(1);
                    if (deck.equals("NotYugi")) {
                        pop.setIsDuelist(false);
                        builders.put(charKey, pop);
                    } else {
                        pop.setIsDuelist(true);
                        builders.put(deckKey, pop);
                    }
                }
            }
        }

        for (Map.Entry<String, DeckPopularityBuilder> entry : builders.entrySet()) {
            out.add(entry.getValue().createDeckPopularity());
        }
        Collections.sort(out);
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
