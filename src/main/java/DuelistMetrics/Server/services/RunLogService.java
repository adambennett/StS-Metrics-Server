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

  public RunLog create(RunLog run) { return this.repo.save(run); }

  public Collection<RunLog> findAll() { return repo.findAll(); }

  public Page<RunLog> findAllPages(Pageable pageable) { return repo.findAll(pageable); }

  public Optional<RunLog> findById(long infoID) { return this.repo.findById(infoID); }

  public Long getA20WinsAll() { return this.repo.getA20WinsAll(); }
  public Long getA20RunsAll() { return this.repo.getA20RunsAll(); }
  public Long getC20WinsAll() { return this.repo.getC20WinsAll(); }
  public Long getC20RunsAll() { return this.repo.getC20RunsAll(); }
  public Long getWinsAll() { return this.repo.getWinsAll(); }
  public Long getRunsAll() { return this.repo.getRunsAll(); }
  public Long getHighestFloorAll() { return this.repo.getHighestFloorAll(); }
  public List<String> getMostKilledByAll() { return this.repo.getMostKilledByAll(); }
  public Long getKaibaRunsAll() { return this.repo.getKaibaRunsAll(); }
  public Long getHighestChallengeAll() { return this.repo.getHighestChallengeAll(); }

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

}
