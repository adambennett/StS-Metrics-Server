package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "offer_card_v2")
public class OfferCardV2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long card_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("cards")
  private PickInfoV2 infoV2;

  private String name;
  private Integer offered;
  private Integer picked;
  private Integer pickVic;

  public OfferCardV2() {}

  public OfferCardV2(String name, int offered, int picked, int pickVic, PickInfoV2 info) {
    this.name = name;
    this.offered = offered;
    this.picked = picked;
    this.pickVic = pickVic;
    this.infoV2 = info;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OfferCardV2 offerCard)) return false;
    return Objects.equals(getInfoV2(), offerCard.getInfoV2()) && Objects.equals(getName(), offerCard.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getInfoV2(), getName());
  }
}
