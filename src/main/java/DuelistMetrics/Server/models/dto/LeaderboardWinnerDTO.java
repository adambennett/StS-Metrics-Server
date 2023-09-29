package DuelistMetrics.Server.models.dto;

public record LeaderboardWinnerDTO(Integer wins, String playerId, String startDeck) {
    public LeaderboardWinnerDTO(Integer wins, String playerId) {
        this(wins, playerId, null);
    }
}
