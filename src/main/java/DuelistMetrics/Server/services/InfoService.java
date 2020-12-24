package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.models.tierScore.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.logging.*;

@Service
public class InfoService {

  private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.InfoService");

  private final InfoRepo repo;
  private final TopInfoBundleRepo bundleRepo;
  private final InfoCardRepo  cardRepo;
  private final InfoRelicRepo relicRepo;
  private final InfoPotionRepo potionRepo;
  private final InfoCreatureRepo creatureRepo;
  private final EventRepo eventRepo;
  private final MiniModRepo miniModRepo;
  private final TierScoreRepo tierRepo;
  private static final ArrayList<String> decks;

  @Autowired
  public InfoService(InfoRepo repo, TopInfoBundleRepo bundleRepo, InfoCardRepo cardRepo, InfoRelicRepo relicRepo, InfoPotionRepo potionRepo, InfoCreatureRepo creatureRepo, MiniModRepo miniModRepo, EventRepo eventRepo, TierScoreRepo scoreRepo) {
    this.repo = repo;
    this.bundleRepo = bundleRepo;
    this.cardRepo = cardRepo;
    this.relicRepo = relicRepo;
    this.potionRepo = potionRepo;
    this.creatureRepo = creatureRepo;
    this.miniModRepo = miniModRepo;
    this.eventRepo = eventRepo;
    this.tierRepo = scoreRepo;
  }

  public String getCardName(String card_id, boolean duelist) {
    if (duelist) {
      Long id = getMostRecentDuelistVersion();
      List<String> out = this.cardRepo.getCardNameDuelist(card_id, id);
      return out.size() > 0 ? out.get(0) : card_id;
    }
    List<String> out = this.cardRepo.getCardName(card_id);
    return out.size() > 0 ? out.get(0) : card_id;
  }

  public Map<String, String> duelistCardNames(List<String> cardIds) {
    return this.cardRepo.lookupDuelistCardNames(cardIds, getMostRecentDuelistVersion());
  }

  public Map<String, String> cardIdMappingArchive(List<Long> infoBundleIds) {
    return getStringStringMap(this.cardRepo.cardIdMappingArchive(infoBundleIds));
  }

  public Map<String, String> relicIdMappingArchive(List<Long> infoBundleIds) {
    return getStringStringMap(this.relicRepo.relicIdMappingArchive(infoBundleIds));
  }

  public Map<String, String> potionIdMappingArchive(List<Long> infoBundleIds) {
    return getStringStringMap(this.potionRepo.potionIdMappingArchive(infoBundleIds));
  }

  private Map<String, String> getStringStringMap(List<String> strings) {
    Map<String, String> out = new HashMap<>();
    for (String s : strings) {
      String[] splice = s.split(",");
      if (!out.containsKey(splice[0])) {
        out.put(splice[0], splice[1]);
      } else {
        logger.info("Duplicate id while getting map of names - ID: " + splice[0]);
      }
    }
    return out;
  }

  public List<List<String>> globalCardListData() {
    List<String> poolsWithGlobalCardLists = new ArrayList<>();
    List<String> globalCardList = cardRepo.getGlobalListOfTrackedCardsForTierScores(getMostRecentDuelistVersion());
    poolsWithGlobalCardLists.add("Random Pool (Small)");
    poolsWithGlobalCardLists.add("Random Pool (Big)");
    poolsWithGlobalCardLists.add("Upgrade Pool");
    List<List<String>> output = new ArrayList<>();
    output.add(poolsWithGlobalCardLists);
    output.add(globalCardList);
    return output;
  }

  public List<String> getModDataFromId(String card_id, boolean duelist) {
    if (duelist) {
      List<String> duelistOut = new ArrayList<>();
      duelistOut.add("Duelist Mod");
      duelistOut.add("Nyoxide");
      return duelistOut;
    }
    Long modId = cardRepo.getAnyBundleIdByCardId(card_id);
    List<String> modInfo = bundleRepo.getModInfoFromInfoId(modId);
    if (modInfo.size() < 1) {
      List<String> unknownOut = new ArrayList<>();
      unknownOut.add("Unknown");
      unknownOut.add("Unknown");
      return unknownOut;
    }
    String modName = modInfo.get(0).split(",")[0];
    StringBuilder authors = new StringBuilder();
    Set<String> uniqueAuthors = new HashSet<>();
    for (int i = 0; i < modInfo.size(); i++) {
      String[] splice = modInfo.get(i).split(",");
      uniqueAuthors.add(splice[1]);
    }
    int counter = 0;
    for (String s : uniqueAuthors) {
      authors.append((counter + 1 < uniqueAuthors.size()) ? s + ", " : s);
      counter++;
    }
    List<String> out = new ArrayList<>();
    out.add(modName);
    out.add(authors.toString());
    return out;
  }

  public List<String> getCardDataFromId(String card_id, boolean duelist) {
    if (duelist) {
      Long id = getMostRecentDuelistVersion();
      List<String> out = this.cardRepo.getCardDataDuelist(card_id, id);
      out.addAll(getPoolsFromDuelistCardId(card_id));
      out.add("TEXT");
      out.add(cardRepo.getCardTextByIdDuelist(card_id, id));
      out.add("NEWLINETEXT");
      out.add(cardRepo.getCardNewLineTextByIdDuelist(card_id, id));
      return out;
    }
    return this.cardRepo.getCardData(card_id);
  }

  public List<String> getPoolsFromDuelistCardId(String card_id) {
    Long duelistModId = getMostRecentDuelistVersion();
    Long infoCardId = cardRepo.getInfoCardIdForPools(card_id, duelistModId);
    return cardRepo.getPoolsFromDuelistCard(infoCardId);
  }

  public Long getMostRecentDuelistVersion() {
    List<ModInfoBundle> duelistMods = bundleRepo.getModInfoBundlesByModNameEquals("Duelist Mod");
    return duelistMods.get(duelistMods.size() - 1).getInfo_bundle_id();
  }

  public Map<String, List<String>> getTrackedCardsForTierScores(String poolName) {
    boolean filterPool = poolName != null && !poolName.equals("");
    List<Long> duelistIds = bundleRepo.getModInfoBundleIdsForAllDuelistVersions();
    List<String> cards;
    if (filterPool) {
      cards = cardRepo.getTrackedCardsForTierScores(poolName, duelistIds);
    } else {
      cards = cardRepo.getTrackedCardsForTierScores(duelistIds);
    }
    Map<String, List<String>> out = new HashMap<>();
    for (String s : cards) {
      String[] splice = s.split(",");
      if (!out.containsKey(splice[1])) {
        out.put(splice[1], new ArrayList<>());
      }
      out.get(splice[1]).add(splice[0]);
    }
    List<List<String>> globals = globalCardListData();
    List<String> poolsWithGlobalCardLists = globals.get(0);
    List<String> globalCardList = globals.get(1);
    for (String globalPool : poolsWithGlobalCardLists) {
      for (String s : globalCardList) {
        if (!out.containsKey(globalPool)) {
          out.put(globalPool, new ArrayList<>());
        }
        out.get(globalPool).add(s);
      }
    }
    return out;
  }

  public void updateAllDuelistEvents(List<String> names) {
    for (String event : names) {
      this.eventRepo.updateDuelistEvents(event);
    }
  }

  public List<MiniMod> getModListFromBundleId(Long id) { return this.miniModRepo.getByBundleId(id); }

  public Long getModInfoBundleFromMiniMod(String modid, String version) { return this.miniModRepo.getBundleId(modid, version); }

  public List<ModInfoBundle> getAllMods() { return this.bundleRepo.findAll(); }

  public List<InfoCard> findAllCards() {
    return cardRepo.findAll();
  }

  public List<InfoPotion> findAllPotions() {
    return potionRepo.findAll();
  }

  public List<InfoCreature> findAllCreatures() {
    return creatureRepo.findAll();
  }

  public List<InfoRelic> findAllRelics() {
    return relicRepo.findAll();
  }

  public String getAnubisVisits() { return this.eventRepo.getNumberOfVisits(); }

  public Map<Integer, Double> getAnubisData() {
    Map<Integer, Double> out = new HashMap<>();
    List<String> choices = this.eventRepo.getScoringChoices();
    if (choices.size() > 0) {
      double totalScore = 0;
      double average = 0;
      for (String s : choices) {
        String[] splice = s.split("Scored: ");
        try {
          int score = Integer.parseInt(splice[1]);
          totalScore += score;
        } catch (NumberFormatException ex) {
          logger.info("Caught bad score! String attempting to parse: " + s);
        }
      }
      average = totalScore > 0 ? totalScore / choices.size() : 0;
      out.put(choices.size(), average);
    }
    return out;
  }

  public PickInfo findInfo(String deck, int asc, int chal) {
    if (decks.contains(deck)) {
      int deckIndex = -1;
      for (int i = 0; i < decks.size(); i++) {
        if (decks.get(i).equals(deck)) {
          deckIndex = i;
          break;
        }
      }
      long generatedID = ((462 * deckIndex) + 1) + (asc * 22) + (chal + 1);
      Optional<PickInfo> generatedInfo = repo.findById(generatedID);
      if (generatedInfo.isPresent()) {
        return generatedInfo.get();
      }
    }
    return null;
  }

  public void create(PickInfo run) { this.repo.save(run); }

  public void createTierScore(ScoredCard scoredCard) { this.tierRepo.save(scoredCard); }

  public List<ScoredCard> getTierDetails(String pool) { return this.tierRepo.getDetails(pool); }

  public List<Map<String, Object>> getTierScores(String pool) { return this.tierRepo.getScores(pool); }

  public Map<String, Map<String, Map<Integer, Integer>>> getAllTierScores() {
    List<Map<String, Object>> data = this.tierRepo.getScores();
    Map<String, Map<String, Object>> toAdd = new HashMap<>();
    Map<String, Map<String, Map<Integer, Integer>>> finalOut = new HashMap<>();
    for (Map<String, Object> map : data) {
      String pool = map.get("pool_name").toString();
      String card_id = map.get("card_id").toString();
      Map<String, Object> nonPoolData = new HashMap<>();
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        if (!entry.getKey().equals("pool_name") && !entry.getKey().equals("card_id")) {
          nonPoolData.put(entry.getKey(), entry.getValue());
        }
      }
      if (toAdd.containsKey(pool)) {
        Map<String, Object> toAddTo = toAdd.get(pool);
        toAddTo.put(card_id, nonPoolData);
      } else {
        Map<String, Object> toAddTo = new HashMap<>();
        toAddTo.put(card_id, nonPoolData);
        toAdd.put(pool, toAddTo);
      }
    }
    for (Map.Entry<String, Map<String, Object>> entry : toAdd.entrySet()) {
      String pool = entry.getKey();
      if (!finalOut.containsKey(pool)) {
        finalOut.put(pool, new HashMap<>());
      }
      Map<String, Map<Integer, Integer>> poolMap = finalOut.get(pool);
      for (Map.Entry<String, Object> innerEntry : entry.getValue().entrySet()) {
        String card_Id = innerEntry.getKey();
        Map<String, Object> innerMap = (Map<String, Object>)innerEntry.getValue();
        int act0 = (int) innerMap.get("act0_score");
        int act1 = (int) innerMap.get("act1_score");
        int act2 = (int) innerMap.get("act2_score");
        int act3 = (int) innerMap.get("act3_score");
        int overall = (int) innerMap.get("overall_score");
        Map<Integer, Integer> scores = new HashMap<>();
        scores.put(-1, overall);
        scores.put(0, act0);
        scores.put(1, act1);
        scores.put(2, act2);
        scores.put(3, act3);
        poolMap.put(card_Id, scores);
      }
    }
    return finalOut;
  }

  public Map<String, String> getCardNamesByPool(String pool) {
    Map<String, String> output = new HashMap<>();
    List<String> names = this.cardRepo.getDuelistCardsFromPool(pool);
    for (String s : names) {
      String[] splice = s.split(",");
      String id = splice[0];
      String name = splice[1];
      if (!output.containsKey(id)) {
        output.put(id, name);
      }
    }
    return output;
  }

  public List<Map<String, Object>> getTierScores(String cardId, String pool) { return this.tierRepo.getScores(cardId, pool); }

  public List<String> getAllModuleVersions() { return this.bundleRepo.getAllModuleVersions(); }

  public List<String> getModList() { return this.bundleRepo.getMods(); }

  public Optional<ModInfoBundle> getModInfo(String id, String version) { return this.bundleRepo.findByModIDAndVersion(id, version); }

  public ModInfoBundle updateQuickFields(ModInfoBundle mod) {
    return this.bundleRepo.save(mod);
  }

  public ModInfoBundle createBundle(ModInfoBundle mod) {
    for (InfoCard c : mod.getCards()) {
      c.setInfo(mod);
    }

    for (InfoRelic r : mod.getRelics()) {
      r.setInfo(mod);
    }

    for (InfoPotion p : mod.getPotions()) {
      p.setInfo(mod);
    }

    for (InfoKeyword k : mod.getKeywords()) {
      k.setInfo(mod);
    }

    for (InfoCreature c : mod.getCreatures()) {
      c.setInfo(mod);
    }
    return this.bundleRepo.save(mod);
  }

  static {
    decks = new ArrayList<>();
    decks.add("Standard Deck");
    decks.add("Dragon Deck");
    decks.add("Naturia Deck");
    decks.add("Spellcaster Deck");
    decks.add("Toon Deck");
    decks.add("Zombie Deck");
    decks.add("Aqua Deck");
    decks.add("Fiend Deck");
    decks.add("Machine Deck");
    decks.add("Warrior Deck");
    decks.add("Insect Deck");
    decks.add("Plant Deck");
    decks.add("Megatype Deck");
    decks.add("Increment Deck");
    decks.add("Creator Deck");
    decks.add("Ojama Deck");
    decks.add("Exodia Deck");
    decks.add("Ascended I");
    decks.add("Ascended II");
    decks.add("Ascended III");
    decks.add("Pharaoh I");
    decks.add("Pharaoh II");
    decks.add("Pharaoh III");
    decks.add("Pharaoh IV");
    decks.add("Pharaoh V");
    decks.add("Random Deck (Small)");
    decks.add("Random Deck (Big)");
    decks.add("Upgrade Deck");
    decks.add("Metronome Deck");
    decks.add("Giant Deck");
    decks.add("Predaplant Deck");
  }

}
