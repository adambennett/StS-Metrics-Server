package DuelistMetrics.Server.models;

public class DisplayObject {

  private String name;
  private Integer picked;
  private Integer pickVic;
  private Double popularity;
  private Double power;

  public DisplayObject(String name, Integer picked, Integer pickVic, Double popularity, Double power) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.popularity = popularity;
    this.power = power;
  }

  public String getName() {
    return name;
  }

  public Integer getPicked() {
    return picked;
  }

  public Integer getPickVic() {
    return pickVic;
  }

  public Double getPopularity() {
    return popularity;
  }

  public Double getPower() {
    return power;
  }
}
