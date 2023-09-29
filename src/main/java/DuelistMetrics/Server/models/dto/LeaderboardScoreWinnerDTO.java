package DuelistMetrics.Server.models.dto;

public record LeaderboardScoreWinnerDTO(Integer score, String playerId, String playerNames, Integer runs, Double averageRunScore) {
    public LeaderboardScoreWinnerDTO(Integer score, String playerId, Integer runs) {
        this(score, playerId, null, runs, score != null && runs != null && runs != 0 ? (double)score / (double)runs : 0);
    }

    public LeaderboardScoreWinnerDTO(LeaderboardScoreWinnerDTO previous, String playerNames) {
        this(previous.score() == null || previous.score() < 0 ? 0 : previous.score(), previous.playerId(), playerNames, previous.runs(), previous.averageRunScore());
    }
}
