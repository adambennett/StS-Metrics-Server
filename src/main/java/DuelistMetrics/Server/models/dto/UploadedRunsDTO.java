package DuelistMetrics.Server.models.dto;

import java.util.ArrayList;
import java.util.List;

public record UploadedRunsDTO(String uuid, Integer runs, String playerNames, List<RunLogFavoriteItem> favoriteDecks, List<RunLogFavoriteItem> favoriteCharacters) {
    public UploadedRunsDTO(String uuid, Integer runs, String playerNames) {
        this(uuid, runs, playerNames, new ArrayList<>(), new ArrayList<>());
    }
}
