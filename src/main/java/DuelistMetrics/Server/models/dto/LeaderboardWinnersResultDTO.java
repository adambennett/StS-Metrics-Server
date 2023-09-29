package DuelistMetrics.Server.models.dto;

import java.util.List;

public record LeaderboardWinnersResultDTO(Integer totalWins,
                                          String playerId,
                                          String playerNames,
                                          Integer rank,
                                          List<String> deckWins) {}
