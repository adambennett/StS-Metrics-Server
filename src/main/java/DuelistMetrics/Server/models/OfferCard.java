package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class OfferCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long card_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("cards")
  private PickInfo info;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("cards")
  private PickInfoV2 infoV2;

  private String name;
  private Integer offered;
  private Integer picked;
  private Integer pickVic;

  public OfferCard() {}

  public OfferCard(String name, int offered, int picked, int pickVic, PickInfoV2 info) {
    this.name = name;
    this.offered = offered;
    this.picked = picked;
    this.pickVic = pickVic;
    this.infoV2 = info;
  }

  public Long getCard_id() {
    return card_id;
  }

  public void setCard_id(Long card_id) {
    this.card_id = card_id;
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

  public Integer getOffered() {
    return offered;
  }

  public void setOffered(Integer offered) {
    this.offered = offered;
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
    if (!(o instanceof OfferCard)) return false;
    OfferCard offerCard = (OfferCard) o;
    return Objects.equals(getInfo(), offerCard.getInfo()) &&
      Objects.equals(getName(), offerCard.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getInfo(), getName());
  }
}
