package DuelistMetrics.Server.services;

import DuelistMetrics.Server.controllers.*;
import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.dto.LeaderboardScoreWinnerDTO;
import DuelistMetrics.Server.models.dto.LeaderboardWinnerDTO;
import DuelistMetrics.Server.models.dto.LeaderboardWinnerDeckListDTO;
import DuelistMetrics.Server.models.dto.LeaderboardWinnersResultDTO;
import DuelistMetrics.Server.models.dto.PlayerNameListDTO;
import DuelistMetrics.Server.models.dto.RunMonthDTO;
import DuelistMetrics.Server.models.tierScore.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class BundleService {

  private TopBundleRepo repo;
  private BundleRepo innerRepo;

  @Autowired
  public BundleService(TopBundleRepo repo, BundleRepo inner) { this.repo = repo; this.innerRepo = inner; }

  public TopBundle create(TopBundle run) { return this.repo.save(run); }

  public Collection<TopBundle> findAll() { return repo.findAll(); }

  public Collection<Bundle> findAllInner() { return innerRepo.findAll(); }

  public Page<TopBundle> findAllPages(Pageable pageable) { return repo.findAll(pageable); }

  public Optional<TopBundle> findById(long infoID) { return this.repo.findById(infoID); }

  public Integer countRunsByCharacterToday(String character) { return this.repo.getRunsByCharacterFromToday(character); }

  public Integer countWinsByCharacterToday(String character) { return this.repo.getWinsByCharacterFromToday(character); }

  public Integer countUniquePlayersByCharacterToday(String character) { return this.innerRepo.numberOfUniquePlayersTodayByCharacter(character); }

  public List<RunMonthDTO> countRunsThisYear() { return this.repo.getRunsByCharacterFromThisYear(null); }

  public List<RunMonthDTO> countRunsByCharacterThisYear(String character) { return this.repo.getRunsByCharacterFromThisYear(character); }

  public TopBundle findByHostAndLocalTime(String host, BigDecimal localTime) {
    var list = this.repo.findByHostAndLocalTime(host, localTime);
    if (list != null && list.size() > 0) {
      return list.get(0);
    }
    return null;
  }

  public String findTopBundleTimeByHostAndLocalTime(String host, BigDecimal localTime) {
    return this.repo.findTimeByHostAndLocalTime(host, localTime).toString();
  }

  public Optional<Bundle> findByIdInner(long ID) { return this.innerRepo.findById(ID); }

  public Integer countRunsInTimeFrame(int endInterval, int startInterval) {
    return this.repo.countRunsInTimeFrame(endInterval, startInterval);
  }

  public Integer countRunsInTimeFrame(int endInterval, int startInterval, String character, boolean isDuelist,
                                      boolean nonDuelist, String timeStart, String timeEnd, String host,
                                      String country, Integer ascensionStart, Integer ascensionEnd,
                                      Integer challengeStart, Integer challengeEnd, Boolean victory,
                                      Integer floorStart, Integer floorEnd, String deck, String killedBy) {
    return this.repo.countRunsInTimeFrameWithFilters(endInterval, startInterval, character, isDuelist, nonDuelist,
            timeStart, timeEnd, host, country, ascensionStart, ascensionEnd, challengeStart, challengeEnd, victory,
            floorStart, floorEnd, deck, killedBy);
  }

  public Date getTimeFrame(int start) {
    return this.repo.getTimeFrameDate(start + 1, start);
  }

  public List<String> getCountries() {
    return innerRepo.getCountries();
  }

  public List<TopBundle> getMostRecentRuns(int amt) { return repo.getMostRecentRuns(amt); }

  private void processTierBundles(List<String> data, Map<String, List<TierBundle>> out) {
    for (String s : data) {
      String[] splice = s.split(",");
      int id = -1;
      int floor = -1;
      try { id = Integer.parseInt(splice[0]); floor = Integer.parseInt(splice[3]); } catch (NumberFormatException ignored) {}
      if (id > -1 && floor > -1) {
        boolean victory = splice[1].equals("true");
        String choiceId = splice[2];
        String startDeck = splice[4];
        TierBundle bundle = new TierBundle(id, victory);
        if (!out.containsKey(startDeck)) {
          out.put(startDeck, new ArrayList<>());
          List<String> newChoices = new ArrayList<>();
          newChoices.add(choiceId);
          bundle.card_choices.put(floor, newChoices);
          out.get(startDeck).add(bundle);
        } else {
          List<TierBundle> bndles = out.get(startDeck);
          boolean updated = false;
          for (TierBundle bnd : bndles) {
            if (bnd.equals(bundle)) {
              updated = true;
              if (!bnd.card_choices.containsKey(floor)) {
                bnd.card_choices.put(floor, new ArrayList<>());
              }
              bnd.card_choices.get(floor).add(choiceId);
            }
          }
          if (!updated) {
            List<String> newChoices = new ArrayList<>();
            newChoices.add(choiceId);
            bundle.card_choices.put(floor, newChoices);
            bndles.add(bundle);
          }
        }
      }
    }
  }

  public Map<String, List<TierBundle>> getBundlesForTierScores(List<String> decks) {
    return getBundlesForTierScores(decks, null, null);
  }

  public Map<String, List<TierBundle>> getBundlesForTierScores(List<String> decks, int ascensionFilter) {
    return getBundlesForTierScores(decks, ascensionFilter, null);
  }

  public Map<String, List<TierBundle>> getBundlesForTierScores(List<String> decks, Integer ascensionFilter, Integer challengeFilter) {
    Map<String, List<TierBundle>> out = new HashMap<>();
    for (String deck : decks) {
      List<String> data;
      if (challengeFilter != null && ascensionFilter != null) {
        data = innerRepo.getBundlesForTierScores(deck, ascensionFilter, challengeFilter);
      } else if (ascensionFilter != null) {
        data = innerRepo.getBundlesForTierScores(deck, ascensionFilter);
      } else if (challengeFilter != null) {
        data = innerRepo.getBundlesForTierScores(challengeFilter, deck);
      } else {
        data = innerRepo.getBundlesForTierScores(deck);
      }
      processTierBundles(data, out);
    }
    return out;
  }

  public TreeMap<String, Integer> getCountryCounts() {
    List<String> query = innerRepo.getCountryCounts();
    TreeMap<String, Integer> out = new TreeMap<>();
    for (String s : query) {
      String[] splice = s.split(",");
      String country = splice[0];
      Locale locale = new Locale("", country);
      String displayCountry = locale.getDisplayCountry();
      try {
        Integer num = Integer.parseInt(splice[1]);
        out.compute(displayCountry, (k,v) -> (v==null) ? num : v+num);
      } catch (NumberFormatException ignored) {
        out.compute(displayCountry, (k,v) -> -99);
      }
    }
    return out;
  }

  public Map<String, String> getCountryNameAndID() {
    List<String> query  = innerRepo.getCountryCounts();
    Map<String, String> output = new HashMap<>();
    LinkedHashMap<String, String> sortedOutput = new LinkedHashMap<>();
    for (String s : query) {
      String[] splice = s.split(",");
      String country = splice[0];
      String displayCountry = new Locale("", country).getDisplayCountry();
      output.put(displayCountry, country);
    }
    TreeMap<String, Integer> sorted = getCountryCounts();
    SortedSet<Map.Entry<String, Integer>> realSorted = BundleController.entriesSortedByValues(sorted);
    for (Map.Entry<String, Integer> entry : realSorted) {
      sortedOutput.put(entry.getKey(), output.get(entry.getKey()));
    }
    return sortedOutput;
  }

  public List<LeaderboardScoreWinnerDTO> getScoreLeaderboardWinners() {
    List<LeaderboardScoreWinnerDTO> output = new ArrayList<>();
    var scoreWinners = this.innerRepo.getScoreLeaderboardWinners();
    List<String> playerIds = scoreWinners.stream().map(LeaderboardScoreWinnerDTO::playerId).toList();
    List<PlayerNameListDTO> playerNameList = this.innerRepo.getPlayerNamesByIds(playerIds);
    HashMap<String, String> playerNameMap = new HashMap<>();
    for (var e : playerNameList) {
      playerNameMap.put(e.playerId(), e.playerNames());
    }
    for (var e : scoreWinners) {
      output.add(new LeaderboardScoreWinnerDTO(e, playerNameMap.getOrDefault(e.playerId(), e.playerId())));
    }
    return output;
  }

  public List<LeaderboardWinnersResultDTO> getWinsLeaderboardWinners(String character, String startDeck, Integer ascension) {
    character = character != null && character.equalsIgnoreCase("The Duelist") ? "THE_DUELIST" : character;
    startDeck = character != null && character.equalsIgnoreCase("THE_DUELIST") ? startDeck : null;
    List<LeaderboardWinnersResultDTO> output = new ArrayList<>();
    List<LeaderboardWinnerDTO> winnerList = this.innerRepo.getWinsLeaderboardWinners(character, startDeck, ascension);
    List<String> playerIds = winnerList.stream().map(LeaderboardWinnerDTO::playerId).toList();
    List<LeaderboardWinnerDTO> allWinnerRunData = this.innerRepo.getWinsLeaderboardWinnerData(playerIds, character, startDeck, ascension);
    HashMap<String, Integer> totalWinsByPlayer = new HashMap<>();
    HashMap<String, HashSet<LeaderboardWinnerDeckListDTO>> deckWinsByPlayer = new HashMap<>();
    List<PlayerNameListDTO> playerNameList = this.innerRepo.getPlayerNamesByIds(playerIds);
    HashMap<String, String> playerNameMap = new HashMap<>();
    for (var e : playerNameList) {
      playerNameMap.put(e.playerId(), e.playerNames());
    }

    for (LeaderboardWinnerDTO entry : winnerList) {
      allWinnerRunData.stream().filter(w -> w.playerId().equalsIgnoreCase(entry.playerId())).forEach(r -> {
        totalWinsByPlayer.compute(entry.playerId(), (k, v) -> v == null ? r.wins() : v + r.wins());
        HashSet<LeaderboardWinnerDeckListDTO> set = deckWinsByPlayer.getOrDefault(entry.playerId(), new HashSet<>());
        set.add(new LeaderboardWinnerDeckListDTO(r.startDeck(), r.wins()));
        deckWinsByPlayer.put(entry.playerId(), set);
      });
    }

    int counter = 1;
    for (LeaderboardWinnerDTO entry : winnerList) {
      var allDeckWinsForPlayer = deckWinsByPlayer.getOrDefault(entry.playerId(), new HashSet<>());
      List<String> formattedDeckWinInfo = new ArrayList<>();
      for (var setEntry : allDeckWinsForPlayer) {
        formattedDeckWinInfo.add(setEntry.deck()+ " (" + setEntry.wins() + ")");
      }
      output.add(new LeaderboardWinnersResultDTO(totalWinsByPlayer.getOrDefault(entry.playerId(), 0), entry.playerId(), playerNameMap.getOrDefault(entry.playerId(), entry.playerId()), counter, formattedDeckWinInfo));
      counter++;
    }
    return output.stream().sorted(Comparator.comparing(LeaderboardWinnersResultDTO::rank)).toList();
  }

}
