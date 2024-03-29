package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.RunCountParams.RunCountParamType;
import DuelistMetrics.Server.models.dto.RunDifficultyBreakdownDTO;
import DuelistMetrics.Server.models.dto.RunLogDTO;
import DuelistMetrics.Server.models.dto.UploadedRunsDTO;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.logging.*;

@Service
public class RunLogService {

  private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.RunLogService");

  private final RunLogRepo repo;
  private final TopBundleRepo topBundleRepo;

  @Autowired
  public RunLogService(RunLogRepo repo, TopBundleRepo topRepo) {
    this.repo = repo;
    this.topBundleRepo = topRepo;
  }

  public List<RunLog> getAllById(List<Long> id) { return this.repo.findAllById(id); }

  public List<RunLog> getAllByChar(String character) { return this.repo.getAllByCharacterNameEquals(character); }

  public List<RunLog> getAllByCountry(String country) { return this.repo.getAllByCountry(country); }

  public List<RunLog> getAllByHost(String host) { return this.repo.getAllByHost(host); }

  public List<RunLog> getAllByAnyOtherChar(String exemptCharacter) { return this.repo.getAllByCharacterNameIsNot(exemptCharacter); }

  public List<RunLog> getAllByTime(String timeStart, String timeEnd) { return this.repo.getRunLogsByFilterDateBetween(timeStart, timeEnd); }

  public List<String> getAllCharacters() { return this.repo.getAllCharacters(); }

  public List<UploadedRunsDTO> getNumberOfRunsByPlayerIds() {
    return this.fillFavoriteDecksAndCharacters(this.repo.getNumberOfRunsByPlayerIds());
  }

  public List<UploadedRunsDTO> getNumberOfDuelistRunsByPlayerIds() {
    return this.fillFavoriteDecksAndCharacters(this.repo.getNumberOfDuelistRunsByPlayerId());
  }

  private List<UploadedRunsDTO> fillFavoriteDecksAndCharacters(List<UploadedRunsDTO> initialList) {
    List<String> uuids = initialList == null ? new ArrayList<>() : initialList.stream().map(UploadedRunsDTO::uuid).toList();
    var favoriteDecks = this.repo.getFavoriteDecksByPlayerIds(uuids);
    var favoriteChars = this.repo.getFavoriteCharsByPlayerIds(uuids);
    List<UploadedRunsDTO> output = new ArrayList<>();
    if (initialList != null) {
      for (var run : initialList) {
        output.add(new UploadedRunsDTO(
                run.uuid(),
                run.runs(),
                run.playerNames(),
                run.mostRecentRun(),
                favoriteDecks.stream().filter(d -> d.playerId().equals(run.uuid())).toList(),
                favoriteChars.stream().filter(d -> d.playerId().equals(run.uuid())).toList()
        ));
      }
    }
    return output;
  }

  public RunLog create(RunLog run) { return this.repo.save(run); }

  public Collection<RunLog> findAll() { return repo.findAll(); }

  public Map<String, List<RunDifficultyBreakdownDTO>> getAscensionBreakdownDataByCharacterName() {
    List<RunDifficultyBreakdownDTO> ascensionData = this.repo.getAscensionRunBreakdownData();
    Map<String, List<RunDifficultyBreakdownDTO>> organized = new HashMap<>();
    for (var run : ascensionData) {
      if (organized.containsKey(run.characterName())) {
        organized.get(run.characterName()).add(run);
      } else {
        List<RunDifficultyBreakdownDTO> list = new ArrayList<>();
        list.add(run);
        organized.put(run.characterName(), list);
      }
    }
    return organized;
  }

  public List<RunDifficultyBreakdownDTO> getChallengeBreakdownData() {
    return this.repo.getChallengeRunBreakdownData();
  }

  public Long countRuns(RunCountParams params) {
    if (params.noTypes) {
      return this.repo.countWithoutFilter();
    }
    var t = params.types;
    if (params.types.uuid() != null && !params.types.uuid().isEmpty()) {
      return this.repo.countAllByPlayerUUID(params.types.uuid(), t.character(), t.duelist(), t.nonDuelist(), t.timeStart(), t.timeEnd(),
              t.host(), t.country(), t.ascensionStart(), t.ascensionEnd(), t.challengeStart(), t.challengeEnd(),
              t.victory(), t.floorStart(), t.floorEnd(), t.deck(), t.killedBy());
    }
    return this.repo.countAllWithFilters(t.character(), t.duelist(), t.nonDuelist(), t.timeStart(), t.timeEnd(),
            t.host(), t.country(), t.ascensionStart(), t.ascensionEnd(), t.challengeStart(), t.challengeEnd(),
            t.victory(), t.floorStart(), t.floorEnd(), t.deck(), t.killedBy());
  }

  public Collection<RunLogDTO> findAll(Integer pageNumber, Integer pageSize, RunCountParams params) {
    int offset = pageNumber * pageSize;
    logger.info("filter params: " + params);
    if (params.noTypes) {
      Collection<RunLogDTO> initialResult = this.repo.findAllWithFilters(offset, pageSize, null, false, false, null,
              null, null, null, null, null, null,
              null, null, null, null, null, null, null);
      return addExtensionDataToRunLogList(initialResult, null);
    }
    RunCountParamType t = params.types;
    Collection<RunLogDTO> initialResult =  this.repo.findAllWithFilters(offset, pageSize, t.character(), t.duelist(), t.nonDuelist(), t.timeStart(),
            t.timeEnd(), t.host(), t.country(), t.ascensionStart(), t.ascensionEnd(), t.challengeStart(),
            t.challengeEnd(), t.victory(), t.floorStart(), t.floorEnd(), t.deck(), t.killedBy(), t.uuid());
    return addExtensionDataToRunLogList(initialResult, t.uuid());
  }

  private Collection<RunLogDTO> addExtensionDataToRunLogList(Collection<RunLogDTO> initialResult, String uuid) {
    List<String> hosts = initialResult.stream().map(RunLogDTO::host).toList();
    List<String> times = initialResult.stream().map(RunLogDTO::filterDate).toList();
    var extensionData = this.topBundleRepo.getRunLogExtensionData(hosts, times, uuid);
    List<RunLogDTO> output = new ArrayList<>();
    for (var run : initialResult) {
      var matchingExtensionData = extensionData.stream().filter(e -> e.time().equals(run.filterDate()) && e.host().equals(run.host()) && (uuid == null || e.uuid().equals(uuid))).toList();
      if (!matchingExtensionData.isEmpty()) {
        var match = matchingExtensionData.get(0);
        output.add(new RunLogDTO(run, match.uuid(), match.utcTime()));
      } else {
        output.add(run);
      }
    }
    return output;
  }

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

  public List<Integer> getCardsBasedOnDeckSet(String deckSet) { return this.repo.getCardsBasedOnDeckSet(deckSet); }

  public List<Integer> getRelicsBasedOnDeckSet(String deckSet) { return this.repo.getRelicsBasedOnDeckSet(deckSet); }

  public List<Integer> getPotionsBasedOnDeckSet(String deckSet) { return this.repo.getPotionsBasedOnDeckSet(deckSet); }

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
