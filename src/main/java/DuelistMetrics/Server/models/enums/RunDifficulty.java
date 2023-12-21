package DuelistMetrics.Server.models.enums;

public enum RunDifficulty {

    ASCENSION("Ascension"),
    CHALLENGE("Challenge");

    private final String display;

    RunDifficulty(String display) {
        this.display = display;
    }

    public String display() {
        return this.display;
    }
}
