package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.RunDifficultyBreakdownDTO;
import DuelistMetrics.Server.models.dto.RunLogDTO;
import DuelistMetrics.Server.models.dto.UploadedRunsDTO;
import jakarta.persistence.*;

@Entity
@NamedNativeQuery(name = "findAllRunDetailsByPlayerUUIDLookup", query = """
SELECT
  rl.run_id,
  rl.ascension,
  rl.challenge,
  rl.character_name AS characterName,
  rl.deck,
  rl.floor,
  rl.host,
  rl.kaiba,
  rl.killed_by AS killedBy,
  rl.time,
  rl.victory,
  rl.country,
  rl.filter_date AS filterDate,
  rl.language
FROM run_log rl
WHERE run_id IN (
SELECT rl.run_id
FROM top_bundle t
JOIN bundle b ON b.top_id = t.event_top_id
JOIN run_log rl ON rl.host = t.host AND rl.filter_date = b.local_time
WHERE t.event_top_id = (SELECT b.top_id FROM bundle b WHERE b.unique_player_id = :uuid)
) AND DATEDIFF(rl.filter_date, CURDATE()) < 14 and
(character_name = :character or :character IS null) and
(character_name = 'THE_DUELIST' or :isDuelist = false) and
(character_name != 'THE_DUELIST' or :nonDuelist = false) and
(rl.host = :host or :host IS null) and
((ascension BETWEEN :ascensionStart AND :ascensionEnd) or :ascensionStart IS null) and
((challenge BETWEEN :challengeStart AND :challengeEnd) or :challengeStart IS null) and
((floor BETWEEN :floorStart AND :floorEnd) or :floorStart IS null) and
(victory = :victory or :victory IS null) and
(deck = :deck or :deck IS null) and
(killed_by = :killedBy or :killedBy IS null) and
((filter_date BETWEEN :timeStart AND :timeEnd) or (:timeStart IS null or :timeEnd IS null)) and
(country = :country or :country IS null)
ORDER BY rl.filter_date
DESC LIMIT :offset, :pageSize
""", resultSetMapping = "runLogDtoMapping")
@SqlResultSetMapping(
        name = "runLogDtoMapping",
        classes = @ConstructorResult(targetClass = RunLogDTO.class,columns = {
                @ColumnResult(name = "run_id", type = Long.class),
                @ColumnResult(name = "ascension", type = Integer.class),
                @ColumnResult(name = "challenge", type = Integer.class),
                @ColumnResult(name = "characterName", type = String.class),
                @ColumnResult(name = "deck", type = String.class),
                @ColumnResult(name = "floor", type = Integer.class),
                @ColumnResult(name = "host", type = String.class),
                @ColumnResult(name = "kaiba", type = Boolean.class),
                @ColumnResult(name = "killedBy", type = String.class),
                @ColumnResult(name = "time", type = String.class),
                @ColumnResult(name = "victory", type = Boolean.class),
                @ColumnResult(name = "country", type = String.class),
                @ColumnResult(name = "filterDate", type = String.class),
                @ColumnResult(name = "language", type = String.class)})
)
@NamedNativeQuery(name = "ascensionRunBreakdownLookup", query = """
SELECT
    'ascension' AS type,
    rl.character_name AS characterName,
    ascension AS level,
    COUNT(*) AS runs
FROM run_log rl
GROUP BY rl.ascension, rl.character_name
ORDER BY rl.character_name, level
""", resultSetMapping = "ascensionDifficultyBreakdownDtoMapping")
@SqlResultSetMapping(
        name = "ascensionDifficultyBreakdownDtoMapping",
        classes = @ConstructorResult(targetClass = RunDifficultyBreakdownDTO.class,columns = {
                @ColumnResult(name = "type", type = String.class),
                @ColumnResult(name = "characterName", type = String.class),
                @ColumnResult(name = "level", type = Integer.class),
                @ColumnResult(name = "runs", type = Integer.class)})
)
@NamedNativeQuery(name = "challengeRunBreakdownLookup", query = """
SELECT
    'challenge' AS type,
    challenge AS level,
    COUNT(*) AS runs
FROM run_log rl
GROUP BY rl.challenge
""", resultSetMapping = "challengeDifficultyBreakdownDtoMapping")
@SqlResultSetMapping(
        name = "challengeDifficultyBreakdownDtoMapping",
        classes = @ConstructorResult(targetClass = RunDifficultyBreakdownDTO.class,columns = {
                @ColumnResult(name = "type", type = String.class),
                @ColumnResult(name = "level", type = Integer.class),
                @ColumnResult(name = "runs", type = Integer.class)})
)
@NamedNativeQuery(name = "getNumberOfRunsByPlayerIdLookup", query = """
SELECT
    unique_player_id AS uuid,
    COUNT(*) AS runs,
    GROUP_CONCAT(DISTINCT t.host SEPARATOR ', ') AS playerNames
FROM bundle b
JOIN top_bundle t ON t.event_top_id = b.top_id
WHERE unique_player_id IS NOT NULL
GROUP BY unique_player_id
ORDER BY runs DESC
""", resultSetMapping = "uploadedRunsDtoMapping")
@SqlResultSetMapping(
        name = "uploadedRunsDtoMapping",
        classes = @ConstructorResult(targetClass = UploadedRunsDTO.class,columns = {
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "runs", type = Integer.class),
                @ColumnResult(name = "playerNames", type = String.class)})
)
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
