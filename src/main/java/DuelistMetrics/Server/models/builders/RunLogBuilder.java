package DuelistMetrics.Server.models.builders;

import DuelistMetrics.Server.models.*;

public class RunLogBuilder {
    private String time;
    private String host;
    private String deck;
    private String killedBy;
    private Integer ascension;
    private Integer challenge;
    private Integer floor;
    private Boolean kaiba;
    private Boolean victory;

    public RunLogBuilder setTime(String time) {
        this.time = time;
        return this;
    }

    public RunLogBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public RunLogBuilder setDeck(String deck) {
        this.deck = deck;
        return this;
    }

    public RunLogBuilder setKilledBy(String killedBy) {
        this.killedBy = killedBy;
        return this;
    }

    public RunLogBuilder setAscension(Integer ascension) {
        this.ascension = ascension;
        return this;
    }

    public RunLogBuilder setChallenge(Integer challenge) {
        this.challenge = challenge;
        return this;
    }

    public RunLogBuilder setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public RunLogBuilder setKaiba(Boolean kaiba) {
        this.kaiba = kaiba;
        return this;
    }

    public RunLogBuilder setVictory(Boolean victory) {
        this.victory = victory;
        return this;
    }

    public RunLog createRunLog() {
        return new RunLog(time, host, deck, killedBy, ascension, challenge, floor, kaiba, victory);
    }
}
