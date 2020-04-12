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

  @Query(value = "SELECT rl.deck, MAX(rl.challenge) FROM run_log rl GROUP BY rl.deck", nativeQuery = true)
  List<String> getHighestChallenge();

}
