package DuelistMetrics.Server.models.dto;

public record FullInfoDisplayObject(String uuid,
                                    String name,
                                    String rarity,
                                    String description,
                                    String flavor,
                                    Integer picked,
                                    Integer pickedVictory,
                                    Double power,
                                    String type) {}
