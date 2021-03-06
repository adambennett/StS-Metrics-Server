package DuelistMetrics.Server.models;

import javax.persistence.*;

@Entity
public class RunLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long run_id;

  private String characterName;
  private String time;
  private String host;
  private String deck;
  private String killedBy;
  private String country;
  private String language;
  private String filterDate;
  private Integer ascension;
  private Integer challenge;
  private Integer floor;
  private Boolean kaiba;
  private Boolean victory;

  public RunLog() {}

  public RunLog(String time, String host, String deck, String killedBy, Integer ascension, Integer challenge, Integer floor, Boolean kaiba, Boolean victory, String characterName, String country, String language, String filterDate) {
    this.time = time;
    this.host = host;
    this.deck = deck;
    this.killedBy = killedBy;
    this.ascension = ascension;
    this.challenge = challenge;
    this.floor = floor;
    this.kaiba = kaiba;
    this.victory = victory;
    this.characterName = characterName;
    this.country = country;
    this.language = language;
    this.filterDate = filterDate;
  }

  public String getCharacterName() {
    return characterName;
  }

  public void setCharacterName(String character) {
    this.characterName = character;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Long getRun_id() {
    return run_id;
  }

  public void setRun_id(Long run_id) {
    this.run_id = run_id;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getDeck() {
    return deck;
  }

  public void setDeck(String deck) {
    this.deck = deck;
  }

  public String getKilledBy() {
    return killedBy;
  }

  public void setKilledBy(String killedBy) {
    this.killedBy = killedBy;
  }

  public Integer getAscension() {
    return ascension;
  }

  public void setAscension(Integer ascension) {
    this.ascension = ascension;
  }

  public Integer getChallenge() {
    return challenge;
  }

  public void setChallenge(Integer challenge) {
    this.challenge = challenge;
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public Boolean getKaiba() {
    return kaiba;
  }

  public void setKaiba(Boolean kaiba) {
    this.kaiba = kaiba;
  }

  public Boolean getVictory() {
    return victory;
  }

  public void setVictory(Boolean victory) {
    this.victory = victory;
  }

  public String getCountry() { return country; }

  public void setCountry(String country) { this.country = country; }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getFilterDate() {
    return filterDate;
  }

  public void setFilterDate(String filterDate) {
    this.filterDate = filterDate;
  }
}
