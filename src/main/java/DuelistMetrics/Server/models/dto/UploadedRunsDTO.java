package DuelistMetrics.Server.models.dto;

import java.util.ArrayList;
import java.util.List;

public record UploadedRunsDTO(String uuid, Integer runs, String playerNames, String mostRecentRun, List<RunLogFavoriteItem> favoriteDecks, List<RunLogFavoriteItem> favoriteCharacters) {
    public UploadedRunsDTO(String uuid, Integer runs, String playerNames, String mostRecentRun) {
        this(uuid, runs, playerNames, mostRecentRun, new ArrayList<>(), new ArrayList<>());
    }
}
