package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
  }

  public void addCard(OfferCard card) {
    this.cards.add(card);
  }

  public void addRelic(OfferRelic relic) {
    this.relics.add(relic);
  }

  public void addPotion(OfferPotion potion) {
    this.potions.add(potion);
  }

  public void addNeow(OfferNeow offerNeow) {
    this.neow.add(offerNeow);
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
