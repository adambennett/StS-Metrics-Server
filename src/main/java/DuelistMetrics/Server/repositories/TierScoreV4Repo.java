package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.TierScoreLookup;
import DuelistMetrics.Server.models.tierScore.ScoredCardKey;
import DuelistMetrics.Server.models.tierScore.ScoredCardV4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TierScoreV4Repo extends JpaRepository<ScoredCardV4, ScoredCardKey> {

    @Query(value = "SELECT pool_name, card_id, card_name, overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card_v4", nativeQuery = true)
    List<Map<String, Object>> getScores();

    @Query(value = "SELECT card_id, card_name, overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card_v4 WHERE pool_name = :pool", nativeQuery = true)
    List<Map<String, Object>> getScores(String pool);

    @Query(value = "SELECT card_name, overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card_v4 WHERE card_id = :card_id AND pool_name = :pool", nativeQuery = true)
    List<Map<String, Object>> getScores(String card_id, String pool);

    @Query(name = "getV4ScoresJPALookup", nativeQuery = true)
    List<TierScoreLookup> getScoresJPA(@Param("cardId") String cardId, @Param("pool") String pool);

}
