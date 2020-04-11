package DuelistMetrics.Server.models.builders;

import DuelistMetrics.Server.models.*;

public class DisplayDeckBuilder {
    private String deck;
    private String mostKilledBy;
    private Integer runs;
    private Integer wins;
    private Integer a20runs;
    private Integer a20wins;
    private Integer c20runs;
    private Integer c20wins;
    private Integer floor;
    private Integer kaiba;

    public DisplayDeckBuilder setDeck(String deck) {
        this.deck = deck;
        return this;
    }

    public DisplayDeckBuilder setMostKilledBy(String mostKilledBy) {
        this.mostKilledBy = mostKilledBy;
        return this;
    }

    public DisplayDeckBuilder setRuns(Integer runs) {
        this.runs = runs;
        return this;
    }

    public DisplayDeckBuilder setWins(Integer wins) {
        this.wins = wins;
        return this;
    }

    public DisplayDeckBuilder setA20runs(Integer a20runs) {
        this.a20runs = a20runs;
        return this;
    }

    public DisplayDeckBuilder setA20wins(Integer a20wins) {
        this.a20wins = a20wins;
        return this;
    }

    public DisplayDeckBuilder setC20runs(Integer c20runs) {
        this.c20runs = c20runs;
        return this;
    }

    public DisplayDeckBuilder setC20wins(Integer c20wins) {
        this.c20wins = c20wins;
        return this;
    }

    public DisplayDeckBuilder setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public DisplayDeckBuilder setKaiba(Integer kaiba) {
        this.kaiba = kaiba;
        return this;
    }

    public DisplayDeck createDisplayDeck() {
        return new DisplayDeck(deck, mostKilledBy, runs, wins, a20runs, a20wins, c20runs, c20wins, floor, kaiba);
    }
}
