package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
public class OfferPotion {

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long potion_id;

  @MapsId
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("potions")
  private PickInfo info;

  private String name;
  private Integer picked;
  private Integer pickVic;

  public OfferPotion() {}

  public OfferPotion(String name, int picked, int pickVic, PickInfo info) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.info = info;
  }

  public Long getPotion_id() {
    return potion_id;
  }

  public void setPotion_id(Long relic_id) {
    this.potion_id = relic_id;
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
}
