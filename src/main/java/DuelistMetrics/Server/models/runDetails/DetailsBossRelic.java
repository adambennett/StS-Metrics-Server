package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.*;

import java.util.*;

public class DetailsBossRelic {

  private String relicId;
  private String relicName;
  private List<SimpleCard> not_picked;

  public DetailsBossRelic(BossRelic relic) {
    this.relicId = relic.getPicked();
    this.not_picked = new ArrayList<>();
    for (String s : relic.getNot_picked()) {
      this.not_picked.add(new SimpleCard(s));
    }
  }

  public String getRelicId() {
    return relicId;
  }

  public void setRelicId(String relicId) {
    this.relicId = relicId;
  }

  public String getRelicName() {
    return relicName;
  }

  public void setRelicName(String relicName) {
    this.relicName = relicName;
  }

  public List<SimpleCard> getNot_picked() {
    return not_picked;
  }

  public void setNot_picked(List<SimpleCard> not_picked) {
    this.not_picked = not_picked;
  }
}
