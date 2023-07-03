package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;


@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long event_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("event_choices")
  private Bundle bundle;

  private Integer damage_healed;
  private Integer gold_gain;
  private Integer damage_taken;
  private Integer max_hp_gain;
  private Integer max_hp_loss;
  private Integer floor;
  private Integer gold_loss;
  private String event_name;
  private String player_choice;
  private Boolean duelist;

  @ElementCollection
  private List<String> cards_obtained;

  @ElementCollection
  private List<String> relics_obtained;

  // Nameless Tomb Specific
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = TombSpentPoints.class)
  @JsonIgnoreProperties("tomb")
  private TombSpentPoints spent_points;
  private Integer magic_reward;
  private Integer greed_reward;
  private Integer war_reward;
  private Integer power_reward;
  private Integer hunger_reward;
  private Integer totalMagicScore;
  private Integer points;
  // END Nameless Tomb Specific


  public Event() {}

  public Long getEvent_id() {
    return event_id;
  }

  public void setEvent_id(Long id) {
    this.event_id = id;
  }

  public Bundle getBundle() {
    return bundle;
  }

  public void setBundle(Bundle bundle) {
    this.bundle = bundle;
  }

  public Integer getDamage_healed() {
    return damage_healed;
  }

  public void setDamage_healed(Integer damage_healed) {
    this.damage_healed = damage_healed;
  }

  public Integer getGold_gain() {
    return gold_gain;
  }

  public void setGold_gain(Integer gold_gain) {
    this.gold_gain = gold_gain;
  }

  public String getPlayer_choice() {
    return player_choice;
  }

  public void setPlayer_choice(String player_choice) {
    this.player_choice = player_choice;
  }

  public Integer getDamage_taken() {
    return damage_taken;
  }

  public void setDamage_taken(Integer damage_taken) {
    this.damage_taken = damage_taken;
  }

  public Integer getMax_hp_gain() {
    return max_hp_gain;
  }

  public void setMax_hp_gain(Integer max_hp_gain) {
    this.max_hp_gain = max_hp_gain;
  }

  public Integer getMax_hp_loss() {
    return max_hp_loss;
  }

  public void setMax_hp_loss(Integer max_hp_loss) {
    this.max_hp_loss = max_hp_loss;
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public Integer getGold_loss() {
    return gold_loss;
  }

  public void setGold_loss(Integer gold_loss) {
    this.gold_loss = gold_loss;
  }

  public String getEvent_name() {
    return event_name;
  }

  public void setEvent_name(String event_name) {
    this.event_name = event_name;
  }

  public Boolean getDuelist() {
    return duelist;
  }

  public void setDuelist(Boolean duelist) {
    this.duelist = duelist;
  }

  public List<String> getCards_obtained() {
    return cards_obtained;
  }

  public void setCards_obtained(List<String> cards_obtained) {
    this.cards_obtained = cards_obtained;
  }

  public List<String> getRelics_obtained() {
    return relics_obtained;
  }

  public void setRelics_obtained(List<String> relics_obtained) {
    this.relics_obtained = relics_obtained;
  }

  public TombSpentPoints getSpent_points() {
    return spent_points;
  }

  public void setSpent_points(TombSpentPoints spent_points) {
    this.spent_points = spent_points;
  }

  public Integer getMagic_reward() {
    return magic_reward;
  }

  public void setMagic_reward(Integer magic_reward) {
    this.magic_reward = magic_reward;
  }

  public Integer getGreed_reward() {
    return greed_reward;
  }

  public void setGreed_reward(Integer greed_reward) {
    this.greed_reward = greed_reward;
  }

  public Integer getWar_reward() {
    return war_reward;
  }

  public void setWar_reward(Integer war_reward) {
    this.war_reward = war_reward;
  }

  public Integer getPower_reward() {
    return power_reward;
  }

  public void setPower_reward(Integer power_reward) {
    this.power_reward = power_reward;
  }

  public Integer getHunger_reward() {
    return hunger_reward;
  }

  public void setHunger_reward(Integer hunger_reward) {
    this.hunger_reward = hunger_reward;
  }

  public Integer getTotalMagicScore() {
    return totalMagicScore;
  }

  public void setTotalMagicScore(Integer totalMagicScore) {
    this.totalMagicScore = totalMagicScore;
  }

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  @Override
  public String toString() {
    return "Event{" +
            "event_id=" + event_id +
            ", bundle=" + bundle +
            ", damage_healed=" + damage_healed +
            ", gold_gain=" + gold_gain +
            ", damage_taken=" + damage_taken +
            ", max_hp_gain=" + max_hp_gain +
            ", max_hp_loss=" + max_hp_loss +
            ", floor=" + floor +
            ", gold_loss=" + gold_loss +
            ", event_name='" + event_name + '\'' +
            ", player_choice='" + player_choice + '\'' +
            ", duelist=" + duelist +
            ", cards_obtained=" + cards_obtained +
            ", relics_obtained=" + relics_obtained +
            ", spent_points=" + spent_points +
            ", magic_reward=" + magic_reward +
            ", greed_reward=" + greed_reward +
            ", war_reward=" + war_reward +
            ", power_reward=" + power_reward +
            ", hunger_reward=" + hunger_reward +
            ", totalMagicScore=" + totalMagicScore +
            '}';
  }
}
