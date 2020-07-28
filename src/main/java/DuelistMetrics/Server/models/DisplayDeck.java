package DuelistMetrics.Server.models;

import DuelistMetrics.Server.util.*;

import java.util.*;

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
  private List<Integer> highestChallengeRunID;
  private List<Integer> highestFloorRunID;

  public DisplayDeck(String deck, String mostKilledBy, Integer runs, Integer wins, Integer a20runs, Integer a20wins, Integer floor, Integer c20runs, Integer c20wins, Integer kaiba, Integer highestChallenge, List<Integer> highestChallengeRunID, List<Integer> highestFloorRunID) {
    this.deck = deck;
    this.mostKilledBy = mostKilledBy;
    this.runs = runs;
    this.wins = wins;
    this.a20runs = a20runs;
    this.a20wins = a20wins;
    this.floor = floor;
    this.c20runs = c20runs;
    this.c20wins = c20wins;
    this.kaiba = kaiba;
    this.highestChallenge = highestChallenge;
    this.highestChallengeRunID = highestChallengeRunID;
    this.highestFloorRunID = highestFloorRunID;
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

  public List<Integer> getHighestChallengeRunID() {
    return highestChallengeRunID;
  }

  public void setHighestChallengeRunID(List<Integer> highestChallengeRunID) {
    this.highestChallengeRunID = highestChallengeRunID;
  }

  public List<Integer> getHighestFloorRunID() {
    return highestFloorRunID;
  }

  public void setHighestFloorRunID(List<Integer> highestFloorRunID) {
    this.highestFloorRunID = highestFloorRunID;
  }

  @Override
  public int compareTo(DisplayDeck o) {
    return (DeckNameProcessor.deckPositions.containsKey(this.deck) && DeckNameProcessor.deckPositions.containsKey(o.deck)) ? DeckNameProcessor.deckPositions.get(this.deck).compareTo(DeckNameProcessor.deckPositions.get(o.getDeck())) : 0;
  }
}
