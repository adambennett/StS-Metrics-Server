package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.models.tierScore.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TierScoreRepo extends JpaRepository<ScoredCard, ScoredCardKey> {

    @Query(value = "SELECT * FROM scored_card WHERE pool_name = :pool", nativeQuery = true)
    List<ScoredCard> getDetails(String pool);

    @Query(value = "SELECT pool_name, card_id, card_name, overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card", nativeQuery = true)
    List<Map<String, Object>> getScores();

    @Query(value = "SELECT card_id, card_name, overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card WHERE pool_name = :pool", nativeQuery = true)
    List<Map<String, Object>> getScores(String pool);

    @Query(value = "SELECT card_name, overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card WHERE card_id = :card_id AND pool_name = :pool", nativeQuery = true)
    List<Map<String, Object>> getScores(String card_id, String pool);

    @Query(value = "SELECT new DuelistMetrics.Server.models.TierScoreLookup(card_name, overall_score, act0_score, act1_score, act2_score, act3_score) FROM ScoredCard WHERE card_id = :cardId AND pool_name = :pool")
    List<TierScoreLookup> getScoresJPA(@Param("cardId") String cardId, @Param("pool") String pool);

}
