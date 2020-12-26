package DuelistMetrics.Server.models.tierScore;

import java.util.*;

public class TierBundle {

    public int id;
    public boolean victory;
    public Map<Integer, List<String>> card_choices;

    public TierBundle(int id, boolean victory) {
        this.id = id;
        this.victory = victory;
        this.card_choices = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TierBundle)) return false;
        TierBundle that = (TierBundle) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
