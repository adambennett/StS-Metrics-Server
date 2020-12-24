package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.*;
import java.util.*;

public class DetailsEvent {

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

  private List<SimpleCard> cards_obtained;
  private List<SimpleCard> relics_obtained;

  // Nameless Tomb Specific
  private TombSpentPoints spent_points;
  private Integer magic_reward;
  private Integer greed_reward;
  private Integer war_reward;
  private Integer power_reward;
  private Integer hunger_reward;
  private Integer totalMagicScore;
  private Integer points;
  // END Nameless Tomb Specific

  public DetailsEvent(Event transfer) {
    this.cards_obtained = new ArrayList<>();
    this.relics_obtained = new ArrayList<>();
    for (String s : transfer.getCards_obtained()) {
      this.cards_obtained.add(new SimpleCard(s));
    }
    for (String s : transfer.getRelics_obtained()) {
      this.relics_obtained.add(new SimpleCard(s));
    }
    this.damage_healed = transfer.getDamage_healed();
    this.gold_gain = transfer.getGold_gain();
    this.damage_taken = transfer.getDamage_taken();
    this.max_hp_gain = transfer.getMax_hp_gain();
    this.max_hp_loss = transfer.getMax_hp_loss();
    this.floor = transfer.getFloor();
    this.gold_loss = transfer.getGold_loss();
    this.event_name = transfer.getEvent_name();
    this.player_choice = transfer.getPlayer_choice();
    this.duelist = transfer.getDuelist();
    this.spent_points = transfer.getSpent_points();
    this.magic_reward = transfer.getMagic_reward();
    this.greed_reward = transfer.getGreed_reward();
    this.war_reward = transfer.getWar_reward();
    this.power_reward = transfer.getPower_reward();
    this.hunger_reward = transfer.getHunger_reward();
    this.totalMagicScore = transfer.getTotalMagicScore();
    this.points = transfer.getPoints();
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

  public String getPlayer_choice() {
    return player_choice;
  }

  public void setPlayer_choice(String player_choice) {
    this.player_choice = player_choice;
  }

  public Boolean getDuelist() {
    return duelist;
  }

  public void setDuelist(Boolean duelist) {
    this.duelist = duelist;
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

  public List<SimpleCard> getCards_obtained() {
    return cards_obtained;
  }

  public void setCards_obtained(List<SimpleCard> cards_obtained) {
    this.cards_obtained = cards_obtained;
  }

  public List<SimpleCard> getRelics_obtained() {
    return relics_obtained;
  }

  public void setRelics_obtained(List<SimpleCard> relics_obtained) {
    this.relics_obtained = relics_obtained;
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
}
