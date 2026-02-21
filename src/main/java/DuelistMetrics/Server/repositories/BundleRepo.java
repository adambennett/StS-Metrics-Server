package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.dto.LeaderboardScoreWinnerDTO;
import DuelistMetrics.Server.models.dto.LeaderboardWinnerDTO;
import DuelistMetrics.Server.models.dto.PlayerNameListDTO;
import DuelistMetrics.Server.models.dto.TierBundleDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface BundleRepo extends JpaRepository<Bundle, Long> {

    @Query(value = "SELECT country, COUNT(*) FROM bundle WHERE country IS NOT NULL GROUP BY country", nativeQuery = true)
    List<String> getCountryCounts();

    @Query(value = "SELECT country FROM bundle WHERE country IS NOT NULL GROUP BY country", nativeQuery = true)
    List<String> getCountries();

    @Query(name = "getTierBundlesLookup", nativeQuery = true)
    List<TierBundleDTO> getBundlesForTierScores(String deck);

    @Query(name = "getTierBundlesWithAscensionLookup", nativeQuery = true)
    List<TierBundleDTO> getBundlesForTierScores(String deck, int ascensionHigherThan);

    @Query(name = "getTierBundlesWithChallengeLookup", nativeQuery = true)
    List<TierBundleDTO> getBundlesForTierScores(int challengeHigherThan, String deck);

    @Query(name = "getTierBundlesWithBothLookup", nativeQuery = true)
    List<TierBundleDTO> getBundlesForTierScores(String deck, int ascensionHigherThan, int challengeHigherThan);

    @Query(name = "getV4TierBundlesLookup", nativeQuery = true)
    List<TierBundleDTO> getV4BundlesForTierScores(String deck);

    @Query(name = "getV4TierBundlesWithAscensionLookup", nativeQuery = true)
    List<TierBundleDTO> getV4BundlesForTierScores(String deck, int ascensionHigherThan);

    @Query(name = "getV4TierBundlesWithChallengeLookup", nativeQuery = true)
    List<TierBundleDTO> getV4BundlesForTierScores(int challengeHigherThan, String deck);

    @Query(name = "getV4TierBundlesWithBothLookup", nativeQuery = true)
    List<TierBundleDTO> getV4BundlesForTierScores(String deck, int ascensionHigherThan, int challengeHigherThan);

    @Query(name = "getA20TierBundlesLookup", nativeQuery = true)
    List<TierBundleDTO> getA20BundlesForTierScores(String deck);

    @Query(name = "getA20TierBundlesWithAscensionLookup", nativeQuery = true)
    List<TierBundleDTO> getA20BundlesForTierScores(String deck, int ascensionHigherThan);

    @Query(name = "getA20TierBundlesWithChallengeLookup", nativeQuery = true)
    List<TierBundleDTO> getA20BundlesForTierScores(int challengeHigherThan, String deck);

    @Query(name = "getA20TierBundlesWithBothLookup", nativeQuery = true)
    List<TierBundleDTO> getA20BundlesForTierScores(String deck, int ascensionHigherThan, int challengeHigherThan);

    @Query(value = """
    SELECT COUNT(*)
    FROM (
        SELECT COUNT(top_id)
        FROM bundle b
        WHERE from_unixtime(b.timestamp) >= DATE_SUB(CURDATE(), INTERVAL 1 DAY) and from_unixtime(b.timestamp) <= DATE_ADD(CURDATE(), INTERVAL 1 DAY) and (:character IS NULL OR b.character_chosen = :character)
        GROUP BY b.unique_player_id
    ) AS uniquePlayers
    """, nativeQuery = true)
    Integer numberOfUniquePlayersTodayByCharacter(String character);

    @Query(name = "getScoreLeaderboardWinnersLookup", nativeQuery = true)
    List<LeaderboardScoreWinnerDTO> getScoreLeaderboardWinners();

    @Query(name = "getWinsLeaderboardWinnersLookup", nativeQuery = true)
    List<LeaderboardWinnerDTO> getWinsLeaderboardWinners(String characterChosen, String startDeck, Integer ascension);

    @Query(name = "getWinsLeaderboardWinnerDataLookup", nativeQuery = true)
    List<LeaderboardWinnerDTO> getWinsLeaderboardWinnerData(List<String> playerIds, String characterChosen, String startDeck, Integer ascension);

    @Query(name = "getPlayerNamesByIdsLookup", nativeQuery = true)
    List<PlayerNameListDTO> getPlayerNamesByIds(List<String> playerIds);

}
