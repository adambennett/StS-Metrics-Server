package DuelistMetrics.Server.models.tierScore;

import DuelistMetrics.Server.models.TierScoreLookup;
import DuelistMetrics.Server.models.dto.ConfigDifferenceDTO;
import jakarta.persistence.*;
import java.util.*;

@Entity
@IdClass(ScoredCardKey.class)
@NamedNativeQuery(name = "getScoresJPALookup", query = """
SELECT
    card_name,
    overall_score,
    act0_score,
    act1_score,
    act2_score,
    act3_score
FROM scored_card
WHERE card_id = :cardId AND
      pool_name = :pool
""", resultSetMapping = "tierScoreLookupDtoMapping")
@SqlResultSetMapping(
        name = "tierScoreLookupDtoMapping",
        classes = @ConstructorResult(targetClass = TierScoreLookup.class,columns = {
                @ColumnResult(name = "card_name", type = String.class),
                @ColumnResult(name = "overall_score", type = Integer.class),
                @ColumnResult(name = "act0_score", type = Integer.class),
                @ColumnResult(name = "act1_score", type = Integer.class),
                @ColumnResult(name = "act2_score", type = Integer.class),
                @ColumnResult(name = "act3_score", type = Integer.class)
        })
)
public class ScoredCard extends GeneralScoringCard {

    @Id
    public String card_id;

    @Id
    public String pool_name;

    public ScoredCard() {}

    public ScoredCard(String card_id, String pool_name) {
        this.card_id = card_id;
        this.pool_name = pool_name;
    }

    @Override
    public ScoredCard generate(String card_id, String pool_name) {
        return new ScoredCard(card_id, pool_name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoredCard that)) return false;
        return card_id.equals(that.card_id) && pool_name.equals(that.pool_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_id, pool_name);
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getPool_name() {
        return pool_name;
    }

    public void setPool_name(String pool_name) {
        this.pool_name = pool_name;
    }
}
