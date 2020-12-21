package DuelistMetrics.Server.models.tierScore;

import java.util.*;

public class PopsCard implements Comparable<PopsCard> {
    public String cardId;
    public int numberOfPicks;
    public int popularityPosition;
    public float percentile;
    public boolean aboveFortyPercentile = false;

    public PopsCard(String cardId) { this.cardId = cardId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PopsCard)) return false;
        PopsCard popsCard = (PopsCard) o;
        return Objects.equals(cardId, popsCard.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }

    @Override
    public int compareTo(PopsCard o) {
        return Integer.compare(this.numberOfPicks, o.numberOfPicks);
    }
}
