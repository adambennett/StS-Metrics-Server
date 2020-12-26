package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
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

}
