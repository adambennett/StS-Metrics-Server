package DuelistMetrics.Server.models;

public class DisplayCard {

  private String name;
  private String cardID;
  private Double popularity;
  private Double power;
  private Integer offered;
  private Integer picked;
  private Integer pickVic;

  public DisplayCard(String name, String cardID, Double popularity, Double power, Integer offered, Integer picked, Integer pickVic) {
    this.name = name;
    this.cardID = cardID;
    this.popularity = popularity;
    this.power = power;
    this.offered = offered;
    this.picked = picked;
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
}
