package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class RunLogService {

  private RunLogRepo repo;

  @Autowired
  public RunLogService(RunLogRepo repo) { this.repo = repo; }

  public List<RunLog> getAllById(List<Long> id) { return this.repo.findAllById(id); }

  public List<RunLog> getAllByChar(String chararacter) { return this.repo.getAllByCharacterNameEquals(chararacter); }

  public List<RunLog> getAllByCountry(String country) { return this.repo.getAllByCountry(country); }

  public List<RunLog> getAllByHost(String host) { return this.repo.getAllByHost(host); }

  public List<RunLog> getAllByAnyOtherChar(String exemptCharacter) { return this.repo.getAllByCharacterNameIsNot(exemptCharacter); }

  public List<RunLog> getAllByTime(String timeStart, String timeEnd) { return this.repo.getRunLogsByFilterDateBetween(timeStart, timeEnd); }

  public List<String> getAllCharacters() { return this.repo.getAllCharacters(); }

  public RunLog create(RunLog run) { return this.repo.save(run); }

  public Collection<RunLog> findAll() { return repo.findAll(); }

  public Page<RunLog> findAllPages(Pageable pageable) { return repo.findAll(pageable); }

  public Optional<RunLog> findById(long infoID) { return this.repo.findById(infoID); }

  public List<String> getDataForPopularity() { return this.repo.getDataForPopularity(); }

  public Optional<Long> getA20WinsAll() { return this.repo.getA20WinsAll(); }
  public Long getA20RunsAll() { return this.repo.getA20RunsAll(); }
  public Long getC20WinsAll() { return this.repo.getC20WinsAll(); }
  public Long getC20RunsAll() { return this.repo.getC20RunsAll(); }
  public Long getWinsAll() { return this.repo.getWinsAll(); }
  public Long getRunsAll() { return this.repo.getRunsAll(); }
  public Long getHighestFloorAll() { return this.repo.getHighestFloorAll(); }
  public List<String> getMostKilledByAll() { return this.repo.getMostKilledByAll(); }
  public Long getKaibaRunsAll() { return this.repo.getKaibaRunsAll(); }
  public Optional<Long> getHighestChallengeAll() { return this.repo.getHighestChallengeAll(); }
  public List<String> getHighestChallengeAllWithId() { return this.repo.getHighestChallengeAllWithId(); }

  public Map<String, Integer> getC20Wins() {
    return getIntegers(this.repo.getC20Wins());
  }
  public Map<String, Integer> getC20Runs() {
    return getIntegers(this.repo.getC20Runs());
  }
  public Map<String, Integer> getWins() {
    return getIntegers(this.repo.getWins());
  }
  public Map<String, Integer> getRuns() {
    return getIntegers(this.repo.getRuns());
  }
  public Map<String, Integer> getHighestFloor() {
    return getIntegers(this.repo.getHighestFloor());
  }
  public List<DeckKilledBy> getMostKilledBy() {
    List<String> kill = this.repo.getMostKilledBy();
    List<DeckKilledBy> output = new ArrayList<>();
    for (String s : kill) {
      String[] splice = s.split(",");
      String deck = splice[0];
      String kb = splice[1];
      int cnt = Integer.parseInt(splice[2]);
      DeckKilledBy dkb = new DeckKilledBy(deck, kb, cnt);
      output.add(dkb);
    }
    return output;
  }
  public Map<String, Integer> getKaibaRuns() {
    return getIntegers(this.repo.getKaibaRuns());
  }

  public Map<String, Integer> getHighestChallenge() {
    return getIntegers(this.repo.getHighestChallenge());
  }

  public Map<String, List<Integer>> getHighestChallengeWithId() {
    return getIdWithDeck(this.repo.getHighestChallengeWithId());
  }

  public Map<String, Integer> getA20Runs() {
    return getIntegers(this.repo.getA20Runs());
  }

  public Map<String, Integer> getA20Wins() {
    return getIntegers(this.repo.getA20Wins());
  }

  private Map<String, Integer> getIntegers(List<String> a20WinsByDeck) {
    Map<String, Integer> output = new HashMap<>();
    for (String s : a20WinsByDeck) {
      String[] splice = s.split(",");
      String deck = splice[0];
      int val = Integer.parseInt(splice[1]);
      output.compute(deck, (k,v) -> (v==null) ? val : v+val);
    }
    return output;
  }

  private Map<String, List<Integer>> getIdWithDeck(List<String> a20WinsByDeck) {
    Map<String, List<Integer>> output = new HashMap<>();
    for (String s : a20WinsByDeck) {
      String[] splice = s.split(",");
      Integer id = Integer.parseInt(splice[0]);
      String deck = splice[1];
      if (!output.containsKey(deck)) {
        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.add(id);
        output.put(deck, tempList);
      } else {
        output.get(deck).add(id);
      }
    }
    return output;
  }

}
