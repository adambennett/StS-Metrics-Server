package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "pick_info_v2")
public class PickInfoV2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferCardV2.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferCardV2> cards;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferRelicV2.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferRelicV2> relics;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferPotionV2.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferPotionV2> potions;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferNeowV2.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferNeowV2> neow;

  private String deck;
  private Integer ascension;
  private Integer challenge;

  @Temporal(TemporalType.TIMESTAMP)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Date created_date;

  @GeneratedValue(strategy = GenerationType.AUTO)
  private String data_hash;

  public PickInfoV2() {}

  public PickInfoV2(String deck, int asc, int chal) {
    this.deck = deck;
    this.ascension = asc;
    this.challenge = chal;
    this.cards = new ArrayList<>();
    this.relics = new ArrayList<>();
    this.potions = new ArrayList<>();
    this.neow = new ArrayList<>();
  }

  public void addCard(OfferCardV2 card) {
    if (!this.cards.contains(card)) { this.cards.add(card); }
    else {
      for (OfferCardV2 c : this.cards) {
        if (c.equals(card)) {
          c.setPicked(c.getPicked() + card.getPicked());
          c.setPickVic(c.getPickVic() + card.getPickVic());
          c.setOffered(c.getOffered() + card.getOffered());
        }
      }
    }
  }

  public void addRelic(OfferRelicV2 relic) {
    if (!this.relics.contains(relic)) { this.relics.add(relic); }
    else {
      for (OfferRelicV2 c : this.relics) {
        if (c.equals(relic)) {
          c.setPicked(c.getPicked() + relic.getPicked());
          c.setPickVic(c.getPickVic() + relic.getPickVic());
        }
      }
    }
  }

  public void addPotion(OfferPotionV2 potion) {
    if (!this.potions.contains(potion)) { this.potions.add(potion); }
    else {
      for (OfferPotionV2 c : this.potions) {
        if (c.equals(potion)) {
          c.setPicked(c.getPicked() + potion.getPicked());
          c.setPickVic(c.getPickVic() + potion.getPickVic());
        }
      }
    }
  }

  public void addNeow(OfferNeowV2 offerNeow) {
    if (!this.neow.contains(offerNeow)) { this.neow.add(offerNeow); }
    else {
      for (OfferNeowV2 c : this.neow) {
        if (c.equals(offerNeow)) {
          c.setPicked(c.getPicked() + offerNeow.getPicked());
          c.setPickVic(c.getPickVic() + offerNeow.getPickVic());
        }
      }
    }
  }

}
