package DuelistMetrics.Server.models.tierScore;

import java.io.*;
import java.util.*;

public class ScoredCardKey implements Serializable {
    private String card_id;
    private String pool_name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoredCardKey)) return false;
        ScoredCardKey that = (ScoredCardKey) o;
        return Objects.equals(card_id, that.card_id) && Objects.equals(pool_name, that.pool_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_id, pool_name);
    }
}
