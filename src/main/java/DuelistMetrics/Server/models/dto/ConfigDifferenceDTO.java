package DuelistMetrics.Server.models.dto;

public record ConfigDifferenceDTO(String category, String type, String setting, String defaultValue, String playerValue) {}
