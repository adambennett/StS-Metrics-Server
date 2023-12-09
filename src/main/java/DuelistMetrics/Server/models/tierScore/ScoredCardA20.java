package DuelistMetrics.Server.models.tierScore;

import DuelistMetrics.Server.models.TierScoreLookup;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

import java.util.Objects;

@Entity
@IdClass(ScoredCardKey.class)
@NamedNativeQuery(name = "getA20ScoresJPALookup", query = """
SELECT
    card_name,
    overall_score,
    act0_score,
    act1_score,
    act2_score,
    act3_score
FROM scored_card_a20
WHERE card_id = :cardId AND
      pool_name = :pool
""", resultSetMapping = "tierScoreA20LookupDtoMapping")
@SqlResultSetMapping(
        name = "tierScoreA20LookupDtoMapping",
        classes = @ConstructorResult(targetClass = TierScoreLookup.class,columns = {
                @ColumnResult(name = "card_name", type = String.class),
                @ColumnResult(name = "overall_score", type = Integer.class),
                @ColumnResult(name = "act0_score", type = Integer.class),
                @ColumnResult(name = "act1_score", type = Integer.class),
                @ColumnResult(name = "act2_score", type = Integer.class),
                @ColumnResult(name = "act3_score", type = Integer.class)
        })
)
public class ScoredCardA20 extends GeneralScoringCard {

    @Id
    public String card_id;

    @Id
    public String pool_name;

    public ScoredCardA20() {}

    public ScoredCardA20(String card_id, String pool_name) {
        this.card_id = card_id;
        this.pool_name = pool_name;
    }

    @Override
    public ScoredCardA20 generate(String card_id, String pool_name) {
        return new ScoredCardA20(card_id, pool_name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoredCardA20 that)) return false;
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
