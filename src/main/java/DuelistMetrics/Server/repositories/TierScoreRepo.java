package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.models.tierScore.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TierScoreRepo extends JpaRepository<ScoredCard, ScoredCardKey> {

    @Query(value = "SELECT * FROM scored_card WHERE pool_name = :pool", nativeQuery = true)
    List<ScoredCard> getDetails(String pool);

    @Query(value = "SELECT card_id, overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card WHERE pool_name = :pool", nativeQuery = true)
    List<Map<String, Object>> getScores(String pool);

    @Query(value = "SELECT overall_score, act0_score, act1_score, act2_score, act3_score FROM scored_card WHERE card_id = :card_id AND pool_name = :pool", nativeQuery = true)
    List<Map<String, Object>> getScores(String card_id, String pool);

}
