package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.*;

public class DetailsRelic {

  private Integer floor;
  private String id;
  private String name;

  public DetailsRelic(Relic transfer) {
    this.floor = transfer.getFloor();
    this.id = transfer.getKey();
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
}
