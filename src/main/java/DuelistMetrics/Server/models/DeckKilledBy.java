package DuelistMetrics.Server.models;

public class DeckKilledBy {

  private String deck;
  private String killed_by;
  private Integer count;

  public DeckKilledBy() {}

  public DeckKilledBy(String deck, String killed_by, Integer count) {
    this.deck = deck;
    this.killed_by = killed_by;
    this.count = count;
  }

  public String getDeck() {
    return deck;
  }

  public void setDeck(String deck) {
    this.deck = deck;
  }

  public String getKilled_by() {
    return killed_by;
  }

  public void setKilled_by(String killed_by) {
    this.killed_by = killed_by;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
