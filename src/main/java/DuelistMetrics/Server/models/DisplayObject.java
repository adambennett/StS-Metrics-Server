package DuelistMetrics.Server.models;

public class DisplayObject {

  private String name;
  private String uuid;
  private Integer picked;
  private Integer pickVic;
  private Double popularity;
  private Double power;

  public DisplayObject(String name, String uuid, Integer picked, Integer pickVic, Double popularity, Double power) {
    this.name = name;
    this.uuid = uuid;
    this.picked = picked;
    this.pickVic = pickVic;
    this.popularity = popularity;
    this.power = power;
  }

  public String getUuid() {
    return uuid;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setPicked(Integer picked) {
    this.picked = picked;
  }

  public void setPickVic(Integer pickVic) {
    this.pickVic = pickVic;
  }

  public void setPopularity(Double popularity) {
    this.popularity = popularity;
  }

  public void setPower(Double power) {
    this.power = power;
  }
}
