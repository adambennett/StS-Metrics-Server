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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pick_info_v2")
public class PickInfoV2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferCard.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferCard> cards;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferRelic.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferRelic> relics;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferPotion.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferPotion> potions;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "infoV2", targetEntity = OfferNeow.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferNeow> neow;

  private String deck;
  private Integer ascension;
  private Integer challenge;

  @Temporal(TemporalType.TIMESTAMP)
  @Generated(GenerationTime.INSERT)
  private Date created_date;

  @Generated(GenerationTime.INSERT)
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

  public void addCard(OfferCard card) {
    if (!this.cards.contains(card)) { this.cards.add(card); }
    else {
      for (OfferCard c : this.cards) {
        if (c.equals(card)) {
          c.setPicked(c.getPicked() + card.getPicked());
          c.setPickVic(c.getPickVic() + card.getPickVic());
          c.setOffered(c.getOffered() + card.getOffered());
        }
      }
    }
  }

  public void addRelic(OfferRelic relic) {
    if (!this.relics.contains(relic)) { this.relics.add(relic); }
    else {
      for (OfferRelic c : this.relics) {
        if (c.equals(relic)) {
          c.setPicked(c.getPicked() + relic.getPicked());
          c.setPickVic(c.getPickVic() + relic.getPickVic());
        }
      }
    }
  }

  public void addPotion(OfferPotion potion) {
    if (!this.potions.contains(potion)) { this.potions.add(potion); }
    else {
      for (OfferPotion c : this.potions) {
        if (c.equals(potion)) {
          c.setPicked(c.getPicked() + potion.getPicked());
          c.setPickVic(c.getPickVic() + potion.getPickVic());
        }
      }
    }
  }

  public void addNeow(OfferNeow offerNeow) {
    if (!this.neow.contains(offerNeow)) { this.neow.add(offerNeow); }
    else {
      for (OfferNeow c : this.neow) {
        if (c.equals(offerNeow)) {
          c.setPicked(c.getPicked() + offerNeow.getPicked());
          c.setPickVic(c.getPickVic() + offerNeow.getPickVic());
        }
      }
    }
  }

  public Long getId() {
    return id;
  }

  public List<OfferCard> getCards() {
    return cards;
  }

  public List<OfferRelic> getRelics() {
    return relics;
  }

  public List<OfferPotion> getPotions() {
    return potions;
  }

  public List<OfferNeow> getNeow() {
    return neow;
  }

  public String getDeck() {
    return deck;
  }

  public Integer getAscension() {
    return ascension;
  }

  public Integer getChallenge() {
    return challenge;
  }

  public Date getCreated_date() {
    return created_date;
  }

  public String getData_hash() {
    return data_hash;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCards(List<OfferCard> cards) {
    this.cards = cards;
  }

  public void setRelics(List<OfferRelic> relics) {
    this.relics = relics;
  }

  public void setPotions(List<OfferPotion> potions) {
    this.potions = potions;
  }

  public void setNeow(List<OfferNeow> neow) {
    this.neow = neow;
  }

  public void setDeck(String deck) {
    this.deck = deck;
  }

  public void setAscension(Integer ascension) {
    this.ascension = ascension;
  }

  public void setChallenge(Integer challenge) {
    this.challenge = challenge;
  }

  public void setCreated_date(Date created_date) {
    this.created_date = created_date;
  }

  public void setData_hash(String data_hash) {
    this.data_hash = data_hash;
  }
}
