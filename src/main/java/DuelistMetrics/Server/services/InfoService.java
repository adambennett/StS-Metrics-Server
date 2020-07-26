package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class InfoService {

  private final InfoRepo repo;
  private final TopInfoBundleRepo bundleRepo;
  private final InfoCardRepo  cardRepo;
  private final InfoRelicRepo relicRepo;
  private final InfoPotionRepo potionRepo;
  private final InfoCreatureRepo creatureRepo;
  private final MiniModRepo miniModRepo;
  private static final ArrayList<String> decks;

  @Autowired
  public InfoService(InfoRepo repo, TopInfoBundleRepo bundleRepo, InfoCardRepo cardRepo, InfoRelicRepo relicRepo, InfoPotionRepo potionRepo, InfoCreatureRepo creatureRepo, MiniModRepo miniModRepo) {
    this.repo = repo;
    this.bundleRepo = bundleRepo;
    this.cardRepo = cardRepo;
    this.relicRepo = relicRepo;
    this.potionRepo = potionRepo;
    this.creatureRepo = creatureRepo;
    this.miniModRepo = miniModRepo;
  }


  public List<ModInfoBundle> getAllMods() { return this.bundleRepo.findAll(); }

  public List<MiniMod> allModsWithoutAuthors() { return miniModRepo.getMiniModsByAuthorIsNull(); }

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
