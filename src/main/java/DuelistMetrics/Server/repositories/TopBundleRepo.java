package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.dto.RunMonthDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Repository
public interface TopBundleRepo extends JpaRepository<TopBundle, Long> {

    @Query(value = "SELECT * FROM top_bundle WHERE top_id > ((SELECT MAX(top_id) FROM top_bundle) - :amt)", nativeQuery = true)
    List<TopBundle> getMostRecentRuns(int amt);

    @Query(value = "SELECT * FROM top_bundle t WHERE t.host = :host AND (SELECT local_time FROM bundle b WHERE b.top_id = t.event_top_id) = :localTime", nativeQuery = true)
    List<TopBundle> findByHostAndLocalTime(String host, BigDecimal localTime);

    @Query(value = "SELECT t.time FROM top_bundle t WHERE t.host = :host AND (SELECT local_time FROM bundle b WHERE b.top_id = t.event_top_id) = :localTime", nativeQuery = true)
    Object findTimeByHostAndLocalTime(String host, BigDecimal localTime);

    @Query(value = "SELECT COUNT(*) FROM top_bundle t " +
            "WHERE (from_unixtime(t.time) BETWEEN DATE_SUB(NOW(), INTERVAL :endInterval HOUR) AND DATE_SUB(NOW(), INTERVAL :startInterval HOUR)) " +
            "ORDER BY COUNT(*) DESC", nativeQuery = true)
    Integer countRunsInTimeFrame(int endInterval, int startInterval);

    @Query(value = "SELECT from_unixtime(t.time) FROM top_bundle t WHERE (from_unixtime(t.time) BETWEEN DATE_SUB(NOW(), INTERVAL :endInterval HOUR) AND DATE_SUB(NOW(), INTERVAL :startInterval HOUR)) LIMIT 1", nativeQuery = true)
    Date getTimeFrameDate(int endInterval, int startInterval);

    @Query(value = """
    SELECT COUNT(*)
    FROM bundle b
    WHERE from_unixtime(b.timestamp) >= DATE_SUB(CURDATE(), INTERVAL 1 DAY) and from_unixtime(b.timestamp) <= DATE_ADD(CURDATE(), INTERVAL 1 DAY) and b.character_chosen = :character
    """, nativeQuery = true)
    Integer getRunsByCharacterFromToday(String character);

    @Query(value = """
    SELECT COUNT(*)
    FROM bundle b
    WHERE from_unixtime(b.timestamp) >= DATE_SUB(CURDATE(), INTERVAL 1 DAY) and from_unixtime(b.timestamp) <= DATE_ADD(CURDATE(), INTERVAL 1 DAY) and b.character_chosen = :character AND b.victory = 1
    """, nativeQuery = true)
    Integer getWinsByCharacterFromToday(String character);

    @Query(name = "getRunsByCharacterFromThisYearLookup", nativeQuery = true)
    List<RunMonthDTO> getRunsByCharacterFromThisYear(String character);

    @Query(value = "SELECT COUNT(*) FROM top_bundle t " +
            "JOIN bundle b on t.top_id = b.top_id " +
            "WHERE " +
            "(from_unixtime(t.time) BETWEEN DATE_SUB(NOW(), INTERVAL :endInterval HOUR) AND DATE_SUB(NOW(), INTERVAL :startInterval HOUR)) and " +
            "DATEDIFF(b.local_time, CURDATE()) < 14 and " +
            "(b.character_chosen = :character or :character IS null) and " +
            "(b.character_chosen = 'THE_DUELIST' or :isDuelist = false) and " +
            "(b.character_chosen != 'THE_DUELIST' or :nonDuelist = false) and " +
            "(t.host = :host or :host IS null) and " +
            "((b.ascension_level BETWEEN :ascensionStart AND :ascensionEnd) or :ascensionStart IS null) and " +
            "((b.challenge_level BETWEEN :challengeStart AND :challengeEnd) or :challengeStart IS null) and " +
            "((b.floor_reached BETWEEN :floorStart AND :floorEnd) or :floorStart IS null) and " +
            "(b.victory = :victory or :victory IS null) and " +
            "(b.starting_deck = :deck or :deck IS null) and " +
            "(b.killed_by = :killedBy or :killedBy IS null) and " +
            "((b.local_time BETWEEN :timeStart AND :timeEnd) or (:timeStart IS null or :timeEnd IS null)) and " +
            "(b.country = :country or :country IS null)", nativeQuery = true)
    Integer countRunsInTimeFrameWithFilters(int endInterval, int startInterval, String character, boolean isDuelist,
                                            boolean nonDuelist, String timeStart, String timeEnd, String host,
                                            String country, Integer ascensionStart, Integer ascensionEnd,
                                            Integer challengeStart, Integer challengeEnd, Boolean victory,
                                            Integer floorStart, Integer floorEnd, String deck, String killedBy);
}
