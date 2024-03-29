package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.dto.LeaderboardScoreWinnerDTO;
import DuelistMetrics.Server.models.dto.LeaderboardWinnerDTO;
import DuelistMetrics.Server.models.dto.PlayerNameListDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface BundleRepo extends JpaRepository<Bundle, Long> {

    @Query(value = "SELECT country, COUNT(*) FROM bundle WHERE country IS NOT NULL GROUP BY country", nativeQuery = true)
    List<String> getCountryCounts();

    @Query(value = "SELECT country FROM bundle WHERE country IS NOT NULL GROUP BY country", nativeQuery = true)
    List<String> getCountries();

    @Query(value = "SELECT top_id, victory, sc.picked, sc.floor, starting_deck FROM bundle JOIN spire_card sc on bundle.top_id = sc.bundle_top_id WHERE starting_deck = :deck AND sc.floor < 51 AND (customized_card_pool = false OR customized_card_pool IS NULL) AND (add_base_game_cards = false OR add_base_game_cards IS NULL)", nativeQuery = true)
    List<String> getBundlesForTierScores(String deck);

    @Query(value = "SELECT top_id, victory, sc.picked, sc.floor, starting_deck FROM bundle JOIN spire_card sc on bundle.top_id = sc.bundle_top_id WHERE starting_deck = :deck AND sc.floor < 51 AND (customized_card_pool = false OR customized_card_pool IS NULL) AND (add_base_game_cards = false OR add_base_game_cards IS NULL) AND ascension_level >= :ascensionHigherThan", nativeQuery = true)
    List<String> getBundlesForTierScores(String deck, int ascensionHigherThan);

    @Query(value = "SELECT top_id, victory, sc.picked, sc.floor, starting_deck FROM bundle JOIN spire_card sc on bundle.top_id = sc.bundle_top_id WHERE starting_deck = :deck AND sc.floor < 51 AND (customized_card_pool = false OR customized_card_pool IS NULL) AND (add_base_game_cards = false OR add_base_game_cards IS NULL) AND challenge_level >= :challengeHigherThan", nativeQuery = true)
    List<String> getBundlesForTierScores(int challengeHigherThan, String deck);

    @Query(value = "SELECT top_id, victory, sc.picked, sc.floor, starting_deck FROM bundle JOIN spire_card sc on bundle.top_id = sc.bundle_top_id WHERE starting_deck = :deck AND sc.floor < 51 AND (customized_card_pool = false OR customized_card_pool IS NULL) AND (add_base_game_cards = false OR add_base_game_cards IS NULL) AND ascension_level >= :ascensionHigherThan AND challenge_level >= :challengeHigherThan", nativeQuery = true)
    List<String> getBundlesForTierScores(String deck, int ascensionHigherThan, int challengeHigherThan);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getV4BundlesForTierScores(String deck);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
          ascension_level >= :ascensionHigherThan AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getV4BundlesForTierScores(String deck, int ascensionHigherThan);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
          challenge_level >= :challengeHigherThan AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getV4BundlesForTierScores(int challengeHigherThan, String deck);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
          ascension_level >= :ascensionHigherThan AND
          challenge_level >= :challengeHigherThan AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getV4BundlesForTierScores(String deck, int ascensionHigherThan, int challengeHigherThan);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
          ascension_level >= 20 AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getA20BundlesForTierScores(String deck);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
           ascension_level >= 20 AND ascension_level >= :ascensionHigherThan AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getA20BundlesForTierScores(String deck, int ascensionHigherThan);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
          ascension_level >= 20 AND
          challenge_level >= :challengeHigherThan AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getA20BundlesForTierScores(int challengeHigherThan, String deck);

    @Query(value = """
    SELECT top_id, victory, sc.picked, sc.floor, starting_deck
    FROM bundle
    JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
    WHERE starting_deck = :deck AND
          sc.floor < 51 AND
          (customized_card_pool = false OR customized_card_pool IS NULL) AND
          (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
          ascension_level >= 20 AND ascension_level >= :ascensionHigherThan AND
          challenge_level >= :challengeHigherThan AND
          (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
    """, nativeQuery = true)
    List<String> getA20BundlesForTierScores(String deck, int ascensionHigherThan, int challengeHigherThan);

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
