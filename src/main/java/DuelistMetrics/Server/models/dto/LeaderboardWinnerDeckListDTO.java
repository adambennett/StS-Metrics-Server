package DuelistMetrics.Server.models.dto;

import java.util.Objects;

public record LeaderboardWinnerDeckListDTO(String deck, Integer wins) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeaderboardWinnerDeckListDTO that)) return false;
        return Objects.equals(deck, that.deck) && Objects.equals(wins, that.wins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, wins);
    }
}
