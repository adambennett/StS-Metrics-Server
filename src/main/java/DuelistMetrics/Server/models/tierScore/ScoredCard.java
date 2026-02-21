package DuelistMetrics.Server.models.tierScore;

import DuelistMetrics.Server.models.TierScoreLookup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
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
public class ScoredCard implements GeneralScoringCard {

    @Id
    public String card_id;

    @Id
    public String pool_name;

    private String card_name;
    private Date lastUpdated;
    private float act0_delta;
    private float act1_delta;
    private float act2_delta;
    private float act3_delta;
    private float act0_win_rate;
    private float act1_win_rate;
    private float act2_win_rate;
    private float act3_win_rate;
    private int act0_wins;
    private int act1_wins;
    private int act2_wins;
    private int act3_wins;
    private int act0_losses;
    private int act1_losses;
    private int act2_losses;
    private int act3_losses;
    private int act0_score;
    private int act1_score;
    private int act2_score;
    private int act3_score;
    private int overall_score;
    private int position;
    private int a0_position;
    private int a1_position;
    private int a2_position;
    private int a3_position;
    private float percentile;
    private float a0_percentile;
    private float a1_percentile;
    private float a2_percentile;
    private float a3_percentile;

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

    public void multOverall(float multi) {
        this.setOverall_score((int) (this.getOverall_score() * multi));
    }

    public void multA0(float multi) {
        this.setAct0_score((int) (this.getAct0_score() * multi));
    }

    public void multA1(float multi) {
        this.setAct1_score((int) (this.getAct1_score() * multi));
    }

    public void multA2(float multi) {
        this.setAct2_score((int) (this.getAct2_score() * multi));
    }

    public void multA3(float multi) {
        this.setAct3_score((int) (this.getAct3_score() * multi));
    }

    public void inc100() {
        this.setOverall_score(this.getOverall_score() + 100);
        this.setAct0_score(this.getAct0_score() + 100);
        this.setAct1_score(this.getAct1_score() + 100);
        this.setAct2_score(this.getAct2_score() + 100);
        this.setAct3_score(this.getAct3_score() + 100);
    }
}
