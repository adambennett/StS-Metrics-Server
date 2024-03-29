package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class OfferNeow {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long neow_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("neow")
  private PickInfo info;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("neow")
  private PickInfoV2 infoV2;

  private String name;
  private Integer picked;
  private Integer pickVic;

  public OfferNeow() {}

  public OfferNeow(String name, int picked, int pickVic, PickInfoV2 info) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.infoV2 = info;
  }

  public Long getNeow_id() {
    return neow_id;
  }

  public void setNeow_id(Long relic_id) {
    this.neow_id = relic_id;
  }

  public PickInfo getInfo() {
    return info;
  }

  public void setInfo(PickInfo info) {
    this.info = info;
  }

  public PickInfoV2 getInfoV2() {
    return infoV2;
  }

  public void setInfoV2(PickInfoV2 infoV2) {
    this.infoV2 = infoV2;
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
    if (!(o instanceof OfferNeow)) return false;
    OfferNeow offerNeow = (OfferNeow) o;
    return Objects.equals(getName(), offerNeow.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
