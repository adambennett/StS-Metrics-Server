package DuelistMetrics.Server.models.dto;

public record TierBundleDTO(
    Integer topId,
    Boolean victory,
    String picked,
    Integer floor,
    String startingDeck,
    Long infoBundleId
) {}
