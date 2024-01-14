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
@Table(name = "offer_neow_v2")
public class OfferNeowV2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long neow_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("neow")
  private PickInfoV2 infoV2;

  private String name;
  private Integer picked;
  private Integer pickVic;

  public OfferNeowV2() {}

  public OfferNeowV2(String name, int picked, int pickVic, PickInfoV2 info) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.infoV2 = info;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OfferNeowV2 offerNeow)) return false;
    return Objects.equals(getName(), offerNeow.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
