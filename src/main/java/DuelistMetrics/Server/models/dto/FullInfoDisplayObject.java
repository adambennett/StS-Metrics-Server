package DuelistMetrics.Server.models.dto;

public record FullInfoDisplayObject(String uuid,
                                    String name,
                                    String rarity,
                                    String description,
                                    String flavor,
                                    Integer picked,
                                    Integer pickedVictory,
                                    Double power,
                                    String type) {
    public FullInfoDisplayObject(String uuid,
                                 Integer picked,
                                 Integer pickedVictory,
                                 Double power,
                                 String type) {
        this(uuid, null, null, null, null, picked, pickedVictory, power, type);
    }
    public FullInfoDisplayObject(String uuid,
                                 String name,
                                 String rarity,
                                 String description,
                                 String flavor) {
        this(uuid, name, rarity, description, flavor, null, null, null, null);
    }
}
