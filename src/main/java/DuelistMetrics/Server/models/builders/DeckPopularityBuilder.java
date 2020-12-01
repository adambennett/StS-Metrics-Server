package DuelistMetrics.Server.models.builders;

import DuelistMetrics.Server.models.*;

public class DeckPopularityBuilder {
    private Integer month;
    private Integer year;
    private Integer amount;
    private String deck;
    private String character;
    private Boolean isDuelist;

    public DeckPopularityBuilder setIsDuelist(Boolean isDuelist) {
        this.isDuelist = isDuelist;
        return this;
    }

    public DeckPopularityBuilder setMonth(Integer month) {
        this.month = month;
        return this;
    }

    public DeckPopularityBuilder setYear(Integer year) {
        this.year = year;
        return this;
    }

    public DeckPopularityBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public DeckPopularityBuilder setDeck(String deck) {
        this.deck = deck;
        return this;
    }

    public DeckPopularityBuilder setCharacter(String character) {
        this.character = character;
        return this;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDeck() {
        return deck;
    }

    public String getCharacter() {
        return character;
    }

    public Boolean getIsDuelist() {
        return isDuelist;
    }

    public DeckPopularity createDeckPopularity() {
        return new DeckPopularity(month, year, amount, deck, character, isDuelist);
    }
}
