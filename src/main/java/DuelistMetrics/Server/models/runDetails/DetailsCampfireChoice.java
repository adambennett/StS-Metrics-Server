package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.*;

public class DetailsCampfireChoice {

  private Integer floor;
  private String id;
  private String name;
  private String key;

  public DetailsCampfireChoice(CampfireChoice choice) {
    this.floor = choice.getFloor();
    this.id = choice.getData();
    this.key = choice.getKey();
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
