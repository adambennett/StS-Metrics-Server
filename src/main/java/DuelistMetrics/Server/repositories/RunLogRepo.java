package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.dto.RunDifficultyBreakdownDTO;
import DuelistMetrics.Server.models.dto.RunLogDTO;
import DuelistMetrics.Server.models.dto.UploadedRunsDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface RunLogRepo extends JpaRepository<RunLog, Long> {

  @Query(value = "SELECT * FROM run_log rl WHERE DATEDIFF(rl.filter_date, CURDATE()) < 14 ORDER BY rl.filter_date DESC LIMIT :offset, :pageSize", nativeQuery = true)
  Collection<RunLog> findAllWithParams(Integer offset, Integer pageSize);

  @Query(value = "SELECT COUNT(*) FROM run_log rl WHERE DATEDIFF(rl.filter_date, CURDATE()) < 14", nativeQuery = true)
  Long countWithoutFilter();

  @Query(value = "SELECT " +
          "COUNT(*) " +
          "FROM run_log rl " +
          "WHERE " +
          "DATEDIFF(rl.filter_date, CURDATE()) < 14 and " +
          "(character_name = :character or :character IS null) and " +
          "(character_name = 'THE_DUELIST' or :isDuelist = false) and " +
          "(character_name != 'THE_DUELIST' or :nonDuelist = false) and " +
          "(rl.host = :host or :host IS null) and " +
          "((ascension BETWEEN :ascensionStart AND :ascensionEnd) or :ascensionStart IS null) and " +
          "((challenge BETWEEN :challengeStart AND :challengeEnd) or :challengeStart IS null) and " +
          "((floor BETWEEN :floorStart AND :floorEnd) or :floorStart IS null) and " +
          "(victory = :victory or :victory IS null) and " +
          "(deck = :deck or :deck IS null) and " +
          "(killed_by = :killedBy or :killedBy IS null) and" +
          "((filter_date BETWEEN :timeStart AND :timeEnd) or (:timeStart IS null or :timeEnd IS null)) and " +
          "(country = :country or :country IS null) ", nativeQuery = true)
  Long countAllWithFilters(String character, boolean isDuelist, boolean nonDuelist, String timeStart, String timeEnd,
                           String host, String country, Integer ascensionStart, Integer ascensionEnd,
                           Integer challengeStart, Integer challengeEnd, Boolean victory, Integer floorStart,
                           Integer floorEnd, String deck, String killedBy);

  @Query(value = "SELECT * FROM run_log rl " +
          "WHERE " +
          "DATEDIFF(rl.filter_date, CURDATE()) < 14 and " +
          "        (character_name = :character or :character IS null) and " +
          "        (character_name = 'THE_DUELIST' or :isDuelist = false) and " +
          "        (character_name != 'THE_DUELIST' or :nonDuelist = false) and " +
          "        (rl.host = :host or :host IS null) and " +
          "        ((ascension BETWEEN :ascensionStart AND :ascensionEnd) or :ascensionStart IS null) and " +
          "        ((challenge BETWEEN :challengeStart AND :challengeEnd) or :challengeStart IS null) and " +
          "        ((floor BETWEEN :floorStart AND :floorEnd) or :floorStart IS null) and " +
          "        (victory = :victory or :victory IS null) and " +
          "        (deck = :deck or :deck IS null) and " +
          "        (killed_by = :killedBy or :killedBy IS null) and " +
          "        ((filter_date BETWEEN :timeStart AND :timeEnd) or (:timeStart IS null or :timeEnd IS null)) and " +
          "        (country = :country or :country IS null) " +
          "ORDER BY rl.filter_date DESC LIMIT :offset, :pageSize", nativeQuery = true)
  Collection<RunLog> findAllWithFilters(Integer offset, Integer pageSize,String character, boolean isDuelist,
                                        boolean nonDuelist, String timeStart, String timeEnd, String host,
                                        String country, Integer ascensionStart, Integer ascensionEnd,
                                        Integer challengeStart, Integer challengeEnd, Boolean victory,
                                        Integer floorStart, Integer floorEnd, String deck, String killedBy);

  @Query(value = """
SELECT
  COUNT(*)
FROM run_log rl
WHERE run_id IN (
    SELECT rl.run_id
    FROM top_bundle t
    JOIN bundle b ON b.top_id = t.event_top_id
    JOIN run_log rl ON rl.host = t.host AND rl.filter_date = b.local_time
    WHERE t.event_top_id = (SELECT b.top_id FROM bundle b WHERE b.unique_player_id = :uuid)
) AND
  DATEDIFF(rl.filter_date, CURDATE()) < 14 and
  (character_name = :character or :character IS null) and
  (character_name = 'THE_DUELIST' or :isDuelist = false) and
  (character_name != 'THE_DUELIST' or :nonDuelist = false) and
  (rl.host = :host or :host IS null) and
  ((ascension BETWEEN :ascensionStart AND :ascensionEnd) or :ascensionStart IS null) and
  ((challenge BETWEEN :challengeStart AND :challengeEnd) or :challengeStart IS null) and
  ((floor BETWEEN :floorStart AND :floorEnd) or :floorStart IS null) and
  (victory = :victory or :victory IS null) and
  (deck = :deck or :deck IS null) and
  (killed_by = :killedBy or :killedBy IS null) and
  ((filter_date BETWEEN :timeStart AND :timeEnd) or (:timeStart IS null or :timeEnd IS null)) and
  (country = :country or :country IS null)
  """, nativeQuery = true)
  Long countAllByPlayerUUID(String uuid, String character, boolean isDuelist, boolean nonDuelist, String timeStart, String timeEnd,
                            String host, String country, Integer ascensionStart, Integer ascensionEnd,
                            Integer challengeStart, Integer challengeEnd, Boolean victory, Integer floorStart,
                            Integer floorEnd, String deck, String killedBy);

  @Query(name = "findAllRunDetailsByPlayerUUIDLookup", nativeQuery = true)
  Collection<RunLogDTO> findAllByPlayerUUID(String uuid, Integer offset, Integer pageSize,String character, boolean isDuelist,
                                            boolean nonDuelist, String timeStart, String timeEnd, String host,
                                            String country, Integer ascensionStart, Integer ascensionEnd,
                                            Integer challengeStart, Integer challengeEnd, Boolean victory,
                                            Integer floorStart, Integer floorEnd, String deck, String killedBy);

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

  @Query(value = "SELECT DISTINCT rl.character_name FROM run_log rl WHERE rl.character_name NOT IN ('THE_DUELIST', 'IRONCLAD', 'DEFECT', 'THE_SILENT', 'WATCHER')", nativeQuery = true)
  List<String> getAllCharacters();

  @Query(value = "SELECT filter_date, deck, character_name FROM run_log", nativeQuery = true)
  List<String> getDataForPopularity();

  @Query(value = "SELECT ic.info_card_id " +
          "FROM info_card_pools icp " +
          "JOIN info_card ic on icp.info_card_info_card_id = ic.info_card_id " +
          "WHERE " +
          "ic.info_info_bundle_id = (SELECT info_bundle_id FROM mod_info_bundle WHERE mod_name = 'Duelist Mod' order by info_bundle_id desc limit 1) " +
          "AND " +
          "icp.pools = :deck ", nativeQuery = true)
  List<Integer> getCardsBasedOnDeckSet(String deck);

  @Query(value = "SELECT ic.info_relic_id " +
          "FROM info_relic_pools icp " +
          "JOIN info_relic ic on icp.info_relic_info_relic_id = ic.info_relic_id " +
          "WHERE " +
          "ic.info_info_bundle_id = (SELECT info_bundle_id FROM mod_info_bundle WHERE mod_name = 'Duelist Mod' order by info_bundle_id desc limit 1) " +
          "AND " +
          "icp.pools = :deck ", nativeQuery = true)
  List<Integer> getRelicsBasedOnDeckSet(String deck);

  @Query(value = "SELECT ic.info_potion_id " +
          "FROM info_potion_pools icp " +
          "JOIN info_potion ic on icp.info_potion_info_potion_id = ic.info_potion_id " +
          "WHERE " +
          "ic.info_info_bundle_id = (SELECT info_bundle_id FROM mod_info_bundle WHERE mod_name = 'Duelist Mod' order by info_bundle_id desc limit 1) " +
          "AND " +
          "icp.pools = :deck ", nativeQuery = true)
  List<Integer> getPotionsBasedOnDeckSet(String deck);

  @Query(name = "ascensionRunBreakdownLookup", nativeQuery = true)
  List<RunDifficultyBreakdownDTO> getAscensionRunBreakdownData();

  @Query(name = "challengeRunBreakdownLookup", nativeQuery = true)
  List<RunDifficultyBreakdownDTO> getChallengeRunBreakdownData();

  @Query(name = "getNumberOfRunsByPlayerIdLookup", nativeQuery = true)
  List<UploadedRunsDTO> getNumberOfRunsByPlayerIds();

}
