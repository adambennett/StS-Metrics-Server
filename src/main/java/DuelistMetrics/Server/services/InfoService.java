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

  private InfoRepo repo;
  private TopInfoBundleRepo bundleRepo;
  private static ArrayList<String> decks;

  @Autowired
  public InfoService(InfoRepo repo, TopInfoBundleRepo bundleRepo) { this.repo = repo; this.bundleRepo = bundleRepo; }

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

  public PickInfo create(PickInfo run) { return this.repo.save(run); }

  public List<String> getAllModuleVersions() { return this.bundleRepo.getAllModuleVersions(); }

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

  public Collection<PickInfo> findAll() { return repo.findAll(); }

  public Page<PickInfo> findAllPages(Pageable pageable) { return repo.findAll(pageable); }

  public Optional<PickInfo> findById(long infoID) { return this.repo.findById(infoID); }

  public Boolean delete(long infoID) { this.repo.deleteById(infoID); return findById(infoID).isPresent(); }

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
