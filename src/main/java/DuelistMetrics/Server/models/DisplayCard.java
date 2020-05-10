package DuelistMetrics.Server.models;

public class DisplayCard {

  private String name;
  private String uuid;
  private String cardID;
  private Double popularity;
  private Double power;
  private Integer offered;
  private Integer picked;
  private Integer pickVic;

  public DisplayCard(String name, String uuid, String cardID, Double popularity, Double power, Integer offered, Integer picked, Integer pickVic) {
    this.name = name;
    this.uuid = uuid;
    this.cardID = cardID;
    this.popularity = popularity;
    this.power = power;
    this.offered = offered;
    this.picked = picked;
    this.pickVic = pickVic;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setCardID(String cardID) {
    this.cardID = cardID;
  }

  public void setPopularity(Double popularity) {
    this.popularity = popularity;
  }

  public void setPower(Double power) {
    this.power = power;
  }

  public void setOffered(Integer offered) {
    this.offered = offered;
  }

  public void setPicked(Integer picked) {
    this.picked = picked;
  }

  public void setPickVic(Integer pickVic) {
    this.pickVic = pickVic;
  }

  public String getName() {
    return name;
  }

  public String getCardID() {
    return cardID;
  }

  public Double getPopularity() {
    return popularity;
  }

  public Double getPower() {
    return power;
  }

  public Integer getOffered() {
    return offered;
  }

  public Integer getPicked() {
    return picked;
  }

  public Integer getPickVic() {
    return pickVic;
  }

  public void setName(String gameName) {
    this.name = gameName;
  }
}
