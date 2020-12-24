package DuelistMetrics.Server.models.tierScore;

import java.util.*;

public class MinimalScoredCard implements Comparable<MinimalScoredCard> {

    public String cardId;
    public int act0_score;
    public int act1_score;
    public int act2_score;
    public int act3_score;
    public int overall_score;

    public MinimalScoredCard(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MinimalScoredCard)) return false;
        MinimalScoredCard that = (MinimalScoredCard) o;
        return cardId.equals(that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }

    @Override
    public int compareTo(MinimalScoredCard o) {
        int overall = -Integer.compare(this.overall_score, o.overall_score);
        if (overall != 0) {
            return overall;
        }
        int name = this.cardId.compareTo(o.cardId);
        if (name != 0) {
            return name;
        }
        int a1 = -Integer.compare(this.act1_score, o.act1_score);
        if (a1 != 0) {
            return a1;
        }
        int a2 = -Integer.compare(this.act2_score, o.act2_score);
        if (a2 != 0) {
            return a2;
        }
        int a3 = -Integer.compare(this.act3_score, o.act3_score);
        if (a3 != 0) {
            return a3;
        }
        return -Integer.compare(this.act0_score, o.act0_score);
    }
}
