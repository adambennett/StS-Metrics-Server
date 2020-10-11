package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface RunLogRepo extends JpaRepository<RunLog, Long> {

  @Query(value = "SELECT rl.deck, SUM(rl.victory = 1) AS yes FROM run_log rl WHERE rl.ascension = 20 GROUP BY rl.deck", nativeQuery = true)
  List<String> getA20Wins();

  @Query(value = "SELECT rl.deck, COUNT(*) FROM run_log rl WHERE rl.ascension = 20 GROUP BY rl.deck", nativeQuery = true)
  List<String> getA20Runs();

  @Query(value = "SELECT rl.deck, SUM(rl.victory = 1) AS yes FROM run_log rl WHERE rl.challenge = 20 GROUP BY rl.deck", nativeQuery = true)
  List<String> getC20Wins();

  @Query(value = "SELECT rl.deck, COUNT(*) FROM run_log rl WHERE rl.challenge = 20 GROUP BY rl.deck", nativeQuery = true)
  List<String> getC20Runs();

  @Query(value = "SELECT rl.deck, SUM(rl.victory = 1) AS yes FROM run_log rl GROUP BY rl.deck", nativeQuery = true)
  List<String> getWins();

  @Query(value = "SELECT rl.deck, COUNT(*) FROM run_log rl GROUP BY rl.deck", nativeQuery = true)
  List<String> getRuns();

  @Query(value = "SELECT rl.deck, MAX(rl.floor) FROM run_log rl GROUP BY rl.deck", nativeQuery = true)
  List<String> getHighestFloor();

  @Query(value = "SELECT deck, killed_by, COUNT(*) AS count FROM run_log WHERE killed_by != 'Self' GROUP BY deck", nativeQuery = true)
  List<String> getMostKilledBy();

  @Query(value = "SELECT rl.deck, COUNT(*) FROM run_log rl WHERE rl.kaiba = 1 GROUP BY rl.deck", nativeQuery = true)
  List<String> getKaibaRuns();

  @Query(value = "SELECT rl.deck, MAX(rl.challenge) FROM run_log rl  WHERE rl.victory = 1 GROUP BY rl.deck", nativeQuery = true)
  List<String> getHighestChallenge();

  @Query(value = "SELECT tt.run_id, tt.deck, tt.challenge FROM run_log tt INNER JOIN (SELECT deck, MAX(challenge) AS MaxChallenge FROM run_log WHERE victory = 1 AND challenge > -1 GROUP BY deck) groupedtt ON tt.deck = groupedtt.deck AND tt.challenge = groupedtt.MaxChallenge AND tt.victory = 1 AND tt.challenge > -1", nativeQuery = true)
  List<String> getHighestChallengeWithId();

  @Query(value = "SELECT SUM(rl.victory = 1) FROM run_log rl WHERE rl.ascension = 20", nativeQuery = true)
  Optional<Long> getA20WinsAll();

  @Query(value = "SELECT COUNT(*) FROM run_log rl WHERE rl.ascension = 20", nativeQuery = true)
  Long getA20RunsAll();

  @Query(value = "SELECT SUM(rl.victory = 1) FROM run_log rl WHERE rl.challenge = 20", nativeQuery = true)
  Long getC20WinsAll();

  @Query(value = "SELECT COUNT(*) FROM run_log rl WHERE rl.challenge = 20", nativeQuery = true)
  Long getC20RunsAll();

  @Query(value = "SELECT SUM(rl.victory = 1) FROM run_log rl", nativeQuery = true)
  Long getWinsAll();

  @Query(value = "SELECT COUNT(*) FROM run_log rl", nativeQuery = true)
  Long getRunsAll();

  @Query(value = "SELECT MAX(rl.floor) FROM run_log rl", nativeQuery = true)
  Long getHighestFloorAll();

  @Query(value = "SELECT killed_by, COUNT(*) FROM run_log WHERE killed_by != 'Self'", nativeQuery = true)
  List<String> getMostKilledByAll();

  List<RunLog> getRunLogsByFilterDateBetween(String timeStart, String timeEnd);

  @Query(value = "SELECT COUNT(*) FROM run_log rl WHERE rl.kaiba = 1", nativeQuery = true)
  Long getKaibaRunsAll();

  @Query(value = "SELECT MAX(rl.challenge) FROM run_log rl WHERE rl.victory = 1", nativeQuery = true)
  Optional<Long> getHighestChallengeAll();

  @Query(value = "SELECT tt.run_id, tt.challenge FROM run_log tt INNER JOIN (SELECT MAX(challenge) AS MaxChallenge FROM run_log WHERE victory = 1 AND challenge > 0) groupedtt ON tt.challenge = groupedtt.MaxChallenge AND tt.victory = 1 AND tt.challenge > 0\n", nativeQuery = true)
  List<String> getHighestChallengeAllWithId();

  List<RunLog> getAllByCharacterNameEquals(String characterName);

  List<RunLog> getAllByCharacterNameIsNot(String characterName);

  List<RunLog> getAllByCountry(String country);

  List<RunLog> getAllByHost(String host);

  @Query(value = "SELECT DISTINCT rl.character_name FROM run_log rl", nativeQuery = true)
  List<String> getAllCharacters();

  @Query(value = "SELECT filter_date, deck, character_name FROM run_log", nativeQuery = true)
  List<String> getDataForPopularity();


}
