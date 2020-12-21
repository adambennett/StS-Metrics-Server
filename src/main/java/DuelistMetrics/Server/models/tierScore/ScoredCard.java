package DuelistMetrics.Server.models.tierScore;

import java.util.*;

public class ScoredCard {

    public String cardId;
    public float act0_delta;
    public float act1_delta;
    public float act2_delta;
    public float act3_delta;
    public float act0_winrate;
    public float act1_winrate;
    public float act2_winrate;
    public float act3_winrate;
    public int act0_wins;
    public int act1_wins;
    public int act2_wins;
    public int act3_wins;
    public int act0_losses;
    public int act1_losses;
    public int act2_losses;
    public int act3_losses;
    public int act0_score;
    public int act1_score;
    public int act2_score;
    public int act3_score;
    public int overall_score;
    public int position;
    public float percentile;

    public ScoredCard(String cardId) {
        this.cardId = cardId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoredCard)) return false;
        ScoredCard that = (ScoredCard) o;
        return cardId.equals(that.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }
}
