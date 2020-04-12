package DuelistMetrics.Server.models;

import DuelistMetrics.Server.util.*;

public class DisplayDeck implements Comparable<DisplayDeck> {

  private final String deck;
  private final String mostKilledBy;
  private final Integer runs;
  private final Integer wins;
  private final Integer a20runs;
  private final Integer a20wins;
  private final Integer floor;
  private Integer c20runs;
  private Integer c20wins;
  private Integer kaiba;
  private Integer highestChallenge;

  public DisplayDeck(String deck, String mostKilledBy, Integer runs, Integer wins, Integer a20runs, Integer a20wins, Integer c20runs, Integer c20wins, Integer floor, Integer kaiba, Integer highestChallenge) {
    this.deck = deck;
    this.mostKilledBy = mostKilledBy;
    this.runs = runs;
    this.wins = wins;
    this.a20runs = a20runs;
    this.a20wins = a20wins;
    this.c20runs = c20runs;
    this.c20wins = c20wins;
    this.floor = floor;
    this.kaiba = kaiba;
    this.highestChallenge = highestChallenge;
  }

  public String getDeck() {
    return deck;
  }

  public Integer getRuns() {
    return runs;
  }

  public Integer getWins() {
    return wins;
  }

  public Integer getA20runs() {
    return a20runs;
  }

  public Integer getA20wins() {
    return a20wins;
  }

  public Integer getC20runs() {
    return c20runs;
  }

  public Integer getC20wins() {
    return c20wins;
  }

  public Integer getFloor() {
    return floor;
  }

  public Integer getKaiba() {
    return kaiba;
  }

  public String getMostKilledBy() {
    return mostKilledBy;
  }

  public void setKaiba(Integer kaiba) {
    this.kaiba = kaiba;
  }

  public void setC20runs(Integer c20runs) {
    this.c20runs = c20runs;
  }

  public void setC20wins(Integer c20wins) {
    this.c20wins = c20wins;
  }

  public Integer getHighestChallenge() {
    return highestChallenge;
  }

  public void setHighestChallenge(Integer highestChallenge) {
    this.highestChallenge = highestChallenge;
  }

  @Override
  public int compareTo(DisplayDeck o) {
    return DeckNameProcessor.deckPositions.get(this.deck).compareTo(DeckNameProcessor.deckPositions.get(o.getDeck()));
  }
}
