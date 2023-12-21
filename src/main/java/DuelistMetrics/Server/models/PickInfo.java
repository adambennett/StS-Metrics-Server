package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.*;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import java.util.*;

@Entity
public class PickInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = OfferCard.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferCard> cards;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = OfferRelic.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferRelic> relics;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = OfferPotion.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferPotion> potions;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = OfferNeow.class)
  @JsonIgnoreProperties("info")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferNeow> neow;

  private String deck;
  private Integer ascension;
  private Integer challenge_level;

  public PickInfo() {}

  public PickInfo(String deck, int asc, int chal) {
    this.deck = deck;
    this.ascension = asc;
    this.challenge_level = chal;
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

  public void setId(Long id) {
    this.id = id;
  }

  public String getDeck() {
    return deck;
  }

  public void setDeck(String deck) {
    this.deck = deck;
  }

  public Integer getAscension() {
    return ascension;
  }

  public void setAscension(Integer ascension) {
    this.ascension = ascension;
  }

  public Integer getChallenge_level() {
    return challenge_level;
  }

  public void setChallenge_level(Integer challenge_level) {
    this.challenge_level = challenge_level;
  }

  public List<OfferCard> getCards() {
    return cards;
  }

  public void setCards(List<OfferCard> cards) {
    this.cards = cards;
  }

  public List<OfferRelic> getRelics() {
    return relics;
  }

  public void setRelics(List<OfferRelic> relics) {
    this.relics = relics;
  }

  public List<OfferPotion> getPotions() {
    return potions;
  }

  public void setPotions(List<OfferPotion> potions) {
    this.potions = potions;
  }

  public List<OfferNeow> getNeow() {
    return neow;
  }

  public void setNeow(List<OfferNeow> neow) {
    this.neow = neow;
  }
}
