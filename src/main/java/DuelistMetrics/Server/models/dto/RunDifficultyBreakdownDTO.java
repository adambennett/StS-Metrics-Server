package DuelistMetrics.Server.models.dto;

import DuelistMetrics.Server.models.enums.RunDifficulty;

public record RunDifficultyBreakdownDTO(RunDifficulty type, String characterName, Integer level, Integer runs) {
    public RunDifficultyBreakdownDTO(String type, String characterName, Integer level, Integer runs) {
        this(type != null ? type.equals("ascension")  ? RunDifficulty.ASCENSION : type.equals("challenge") ? RunDifficulty.CHALLENGE : null : null,
                characterName, level, runs);
    }
    public RunDifficultyBreakdownDTO(String type, Integer level, Integer runs) {
        this(type, "THE_DUELIST", level, runs);
    }
}
