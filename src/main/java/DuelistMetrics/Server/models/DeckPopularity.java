package DuelistMetrics.Server.models;

public class DeckPopularity implements Comparable<DeckPopularity> {

    private Integer month;
    private Integer year;
    private Integer amount;
    private String deck;
    private String character;
    private Boolean isDuelist;

    public DeckPopularity() {}

    public DeckPopularity(Integer month, Integer year, Integer amount, String deck, String character, Boolean isDuelist) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.deck = deck;
        this.isDuelist = isDuelist;
        this.character = character;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Boolean getIsDuelist() {
        return isDuelist;
    }

    public void setIsDuelist(Boolean duelist) {
        isDuelist = duelist;
    }

    @Override
    public String toString() {
        return this.deck.equals("NotYugi") ?
                "\nDeckPopularity{" +
                        "month=" + month +
                        ", year=" + year +
                        ", amount=" + amount +
                        ", character='" + character + '\'' +
                        '}'

                :

                "\nDeckPopularity{" +
                "month=" + month +
                ", year=" + year +
                ", amount=" + amount +
                ", deck='" + deck + '\'' +
                '}';
    }

    @Override
    public int compareTo(DeckPopularity o) {
        int yearCompare = this.year.compareTo(o.getYear());
        if (yearCompare == 0) {
            int monthCompare = this.month.compareTo(o.getMonth());
            if (monthCompare == 0) {
                if (this.deck.equals("NotYugi") && o.getDeck().equals("NotYugi")) {
                    return this.character.compareTo(o.getCharacter());
                } else if (!this.deck.equals("NotYugi") && !o.getDeck().equals("NotYugi")) {
                    return this.deck.compareTo(o.getDeck());
                } else {
                    return this.deck.equals("NotYugi") ? 1 : -1;
                }
            }
            return monthCompare;
        }
        return yearCompare;
    }
}
