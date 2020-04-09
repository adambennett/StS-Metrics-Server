package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class OfferRelic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relic_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("relics")
  private PickInfo info;

  private String name;
  private Integer picked;
  private Integer pickVic;

  public OfferRelic() {}

  public OfferRelic(String name, int picked, int pickVic, PickInfo info) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.info = info;
  }

  public Long getRelic_id() {
    return relic_id;
  }

  public void setRelic_id(Long relic_id) {
    this.relic_id = relic_id;
  }

  public PickInfo getInfo() {
    return info;
  }

  public void setInfo(PickInfo info) {
    this.info = info;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPicked() {
    return picked;
  }

  public void setPicked(Integer picked) {
    this.picked = picked;
  }

  public Integer getPickVic() {
    return pickVic;
  }

  public void setPickVic(Integer pickVic) {
    this.pickVic = pickVic;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OfferRelic)) return false;
    OfferRelic that = (OfferRelic) o;
    return Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
