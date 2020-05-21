package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.util.*;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.math.*;
import java.util.*;

@Entity
public class Bundle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long top_id;

  @OneToOne(fetch = FetchType.EAGER, mappedBy = "event")
  @JsonIgnoreProperties("event")
  private TopBundle top;

  private String build_version;
  private String character_chosen;
  private String duelistmod_version;
  private String killed_by;
  private String local_time;
  private String neow_bonus;
  private String neow_cost;
  private String play_id;
  private String pool_fill;
  private String seed_played;
  private String starting_deck;

  private Boolean add_base_game_cards;
  private Boolean allow_boosters;
  private Boolean always_boosters;
  private Boolean bonus_puzzle_summons;
  private Boolean challenge_mode;
  private Boolean chose_seed;
  private Boolean customized_card_pool;
  private Boolean duelist_curses;
  private Boolean encounter_duelist_enemies;
  private Boolean is_ascension_mode;
  private Boolean is_beta;
  private Boolean is_daily;
  private Boolean is_endless;
  private Boolean is_prod;
  private Boolean is_trial;
  private Boolean playing_as_kaiba;
  private Boolean reduced_basic;
  private Boolean remove_card_rewards;
  private Boolean remove_creator;
  private Boolean remove_exodia;
  private Boolean remove_ojama;
  private Boolean remove_toons;
  private Boolean unlock_all_decks;
  private Boolean victory;

  private Integer ascension_level;
  private Integer campfire_rested;
  private Integer campfire_upgraded;
  private Integer challenge_level;
  private Integer circlet_count;
  private Integer floor_reached;
  private Integer gold;
  private Integer highest_max_summons;
  private Integer number_of_monsters;
  private Integer number_of_resummons;
  private Integer number_of_spells;
  private Integer number_of_traps;
  private Integer playtime;
  private Integer purchased_purges;
  private Integer score;
  private Integer total_synergy_tributes;
  private Integer win_rate;

  private BigInteger player_experience;
  private BigInteger seed_source_timestamp;
  private BigInteger timestamp;

  @ElementCollection
  private List<Integer> current_hp_per_floor;

  @ElementCollection
  private List<Integer> gold_per_floor;

  @ElementCollection
  private List<Integer> item_purchase_floors;

  @ElementCollection
  private List<Integer> items_purged_floors;

  @ElementCollection
  private List<Integer> max_hp_per_floor;

  @ElementCollection
  private List<Integer> potions_floor_spawned;

  @ElementCollection
  private List<Integer> potions_floor_usage;

  @ElementCollection
  private List<String> items_purchased;

  @ElementCollection
  private List<String> items_purged;

  @ElementCollection
  private List<String> master_deck;

  @ElementCollection
  private List<String> path_per_floor;

  @ElementCollection
  private List<String> path_taken;

  @ElementCollection
  private List<String> relics;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = MiniMod.class)
  @JsonIgnoreProperties("bundle")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<MiniMod> modList = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = BossRelic.class)
  @JsonIgnoreProperties("bundle")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<BossRelic> boss_relics;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = Event.class)
  @JsonIgnoreProperties("bundle")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Event> event_choices;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = SpireCard.class)
  @JsonIgnoreProperties("bundle")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<SpireCard> card_choices;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = Potion.class)
  @JsonIgnoreProperties("bundle")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Potion> potions_obtained;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = Relic.class)
  @JsonIgnoreProperties("bundle")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Relic> relics_obtained;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = CampfireChoice.class)
  @JsonIgnoreProperties("bundle")
  @Fetch(value = FetchMode.SUBSELECT)
  private List<CampfireChoice> campfire_choices;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bundle", targetEntity = DamageInfo.class)
  @JsonIgnoreProperties("bundle")
  private List<DamageInfo> damage_taken;

  public Bundle() {}

  public Bundle(TopBundle top) {
    Bundle event = top.getEvent();
    this.top = top;
    this.build_version= event.getBuild_version();
    this.character_chosen= event.getCharacter_chosen();
    this.duelistmod_version= event.getDuelistmod_version();
    this.killed_by= event.getKilled_by();
    this.local_time= event.getLocal_time();
    this.neow_bonus= event.getNeow_bonus();
    this.neow_cost= event.getNeow_cost();
    this.play_id= event.getPlay_id();
    this.pool_fill= event.getPool_fill();
    this.seed_played= event.getSeed_played();
    this.starting_deck= event.getStarting_deck();
    this.add_base_game_cards= event.getAdd_base_game_cards();
    this.allow_boosters= event.getAllow_boosters();
    this.always_boosters= event.getAlways_boosters();
    this.bonus_puzzle_summons= event.getBonus_puzzle_summons();
    this.challenge_mode= event.getChallenge_mode();
    this.chose_seed= event.getChose_seed();
    this.customized_card_pool= event.getCustomized_card_pool();
    this.duelist_curses= event.getDuelist_curses();
    this.encounter_duelist_enemies= event.getEncounter_duelist_enemies();
    this.is_ascension_mode= event.getIs_ascension_mode();
    this.is_beta= event.getIs_beta();
    this.is_daily= event.getIs_daily();
    this.is_endless= event.getIs_endless();
    this.is_prod= event.getIs_prod();
    this.is_trial= event.getIs_trial();
    this.playing_as_kaiba= event.getPlaying_as_kaiba();
    this.reduced_basic= event.getReduced_basic();
    this.remove_card_rewards= event.getRemove_card_rewards();
    this.remove_creator= event.getRemove_creator();
    this.remove_exodia= event.getRemove_exodia();
    this.remove_ojama= event.getRemove_ojama();
    this.remove_toons= event.getRemove_toons();
    this.unlock_all_decks= event.getUnlock_all_decks();
    this.victory= event.getVictory();
    this.ascension_level= event.getAscension_level();
    this.campfire_rested= event.getCampfire_rested();
    this.campfire_upgraded= event.getCampfire_upgraded();
    this.circlet_count= event.getCirclet_count();
    this.floor_reached= event.getFloor_reached();
    this.gold= event.getGold();
    this.highest_max_summons= event.getHighest_max_summons();
    this.number_of_monsters= event.getNumber_of_monsters();
    this.number_of_resummons= event.getNumber_of_resummons();
    this.number_of_spells= event.getNumber_of_spells();
    this.number_of_traps= event.getNumber_of_traps();
    this.playtime= event.getPlaytime();
    this.purchased_purges= event.getPurchased_purges();
    this.score= event.getScore();
    this.total_synergy_tributes= event.getTotal_synergy_tributes();
    this.win_rate= event.getWin_rate();
    this.player_experience= event.getPlayer_experience();
    this.seed_source_timestamp= event.getSeed_source_timestamp();
    this.timestamp= event.getTimestamp();
    this.current_hp_per_floor= event.getCurrent_hp_per_floor();
    this.gold_per_floor= event.getGold_per_floor();
    this.item_purchase_floors= event.getItem_purchase_floors();
    this.items_purged_floors= event.getItems_purged_floors();
    this.max_hp_per_floor= event.getMax_hp_per_floor();
    this.potions_floor_spawned= event.getPotions_floor_spawned();
    this.potions_floor_usage= event.getPotions_floor_usage();
    this.items_purchased= event.getItems_purchased();
    this.items_purged= event.getItems_purged();
    this.master_deck= event.getMaster_deck();
    this.path_per_floor= event.getPath_per_floor();
    this.path_taken= event.getPath_taken();
    this.relics= event.getRelics();
    this.boss_relics= event.getBoss_relics();
    this.event_choices= event.getEvent_choices();
    this.card_choices= event.getCard_choices();
    this.potions_obtained= event.getPotions_obtained();
    this.relics_obtained= event.getRelics_obtained();
    this.campfire_choices= event.getCampfire_choices();
    this.damage_taken= event.getDamage_taken();
    this.modList = event.getModList();
  }

  public void removeDisallowedRelics() {
    List<String> newList = new ArrayList<>();
    for (String r : this.relics) {
      if (RelicFilter.getInstance().allowed(r)) {
        newList.add(r);
      }
    }
    this.relics.clear();
    this.relics.addAll(newList);
  }

  public void updateChildren() {
    for (BossRelic r : this.boss_relics) {
      r.setBundle(this);
    }
    for (Event r : this.event_choices) {
      r.setBundle(this);
    }
    for (SpireCard r : this.card_choices) {
      r.setBundle(this);
    }
    for (Potion r : this.potions_obtained) {
      r.setBundle(this);
    }

    for (Relic r : this.relics_obtained) {
        r.setBundle(this);
    }

    for (CampfireChoice r : this.campfire_choices) {
      r.setBundle(this);
    }
    for (DamageInfo r : this.damage_taken) {
      r.setBundle(this);
    }
    for (DamageInfo r : this.damage_taken) {
      r.setBundle(this);
    }

    for (MiniMod mod : this.modList) {
      mod.setBundle(this);
    }
  }

  public Long getTop_id() {
    return top_id;
  }

  public void setTop_id(Long top_id) {
    this.top_id = top_id;
  }

  public List<MiniMod> getModList() {
    return modList;
  }

  public void setModList(List<MiniMod> modList) {
    this.modList = modList;
  }

  public void setTop(TopBundle top) {
    this.top = top;
  }

  public void setBoss_relics(List<BossRelic> boss_relics) {
    this.boss_relics = boss_relics;
  }

  public void setEvent_choices(List<Event> event_choices) {
    this.event_choices = event_choices;
  }

  public void setCard_choices(List<SpireCard> card_choices) {
    this.card_choices = card_choices;
  }

  public void setPotions_obtained(List<Potion> potions_obtained) {
    this.potions_obtained = potions_obtained;
  }

  public void setRelics_obtained(List<Relic> relics_obtained) {
    this.relics_obtained = relics_obtained;
  }

  public void setCampfire_choices(List<CampfireChoice> campfire_choices) {
    this.campfire_choices = campfire_choices;
  }

  public void setDamage_taken(List<DamageInfo> damage_taken) {
    this.damage_taken = damage_taken;
  }

  public void setBuild_version(String build_version) {
    this.build_version = build_version;
  }

  public void setCharacter_chosen(String character_chosen) {
    this.character_chosen = character_chosen;
  }

  public void setDuelistmod_version(String duelistmod_version) { this.duelistmod_version = duelistmod_version; }

  public void setKilled_by(String killed_by) {
    this.killed_by = killed_by;
  }

  public void setLocal_time(String local_time) {
    this.local_time = local_time;
  }

  public void setNeow_bonus(String neow_bonus) {
    this.neow_bonus = neow_bonus;
  }

  public void setNeow_cost(String neow_cost) {
    this.neow_cost = neow_cost;
  }

  public void setPlay_id(String play_id) {
    this.play_id = play_id;
  }

  public void setSeed_played(String seed_played) {
    this.seed_played = seed_played;
  }

  public void setStarting_deck(String starting_deck) {
    this.starting_deck = starting_deck;
  }

  public void setAdd_base_game_cards(Boolean add_base_game_cards) {
    this.add_base_game_cards = add_base_game_cards;
  }

  public void setAllow_boosters(Boolean allow_boosters) {
    this.allow_boosters = allow_boosters;
  }

  public void setAlways_boosters(Boolean always_boosters) {
    this.always_boosters = always_boosters;
  }

  public void setBonus_puzzle_summons(Boolean bonus_puzzle_summons) {
    this.bonus_puzzle_summons = bonus_puzzle_summons;
  }

  public void setChallenge_mode(Boolean challenge_mode) {
    this.challenge_mode = challenge_mode;
  }

  public void setChose_seed(Boolean chose_seed) {
    this.chose_seed = chose_seed;
  }

  public void setCustomized_card_pool(Boolean customized_card_pool) {
    this.customized_card_pool = customized_card_pool;
  }

  public void setDuelist_curses(Boolean duelist_curses) {
    this.duelist_curses = duelist_curses;
  }

  public void setEncounter_duelist_enemies(Boolean encounter_duelist_enemies) {
    this.encounter_duelist_enemies = encounter_duelist_enemies;
  }

  public void setIs_ascension_mode(Boolean is_ascension_mode) {
    this.is_ascension_mode = is_ascension_mode;
  }

  public void setIs_beta(Boolean is_beta) {
    this.is_beta = is_beta;
  }

  public void setIs_daily(Boolean is_daily) {
    this.is_daily = is_daily;
  }

  public void setIs_endless(Boolean is_endless) {
    this.is_endless = is_endless;
  }

  public void setIs_prod(Boolean is_prod) {
    this.is_prod = is_prod;
  }

  public void setIs_trial(Boolean is_trial) {
    this.is_trial = is_trial;
  }

  public void setPlaying_as_kaiba(Boolean playing_as_kaiba) {
    this.playing_as_kaiba = playing_as_kaiba;
  }

  public void setPool_fill(String pool_fill) {
    this.pool_fill = pool_fill;
  }

  public void setReduced_basic(Boolean reduced_basic) {
    this.reduced_basic = reduced_basic;
  }

  public void setRemove_card_rewards(Boolean remove_card_rewards) {
    this.remove_card_rewards = remove_card_rewards;
  }

  public void setRemove_creator(Boolean remove_creator) {
    this.remove_creator = remove_creator;
  }

  public void setRemove_exodia(Boolean remove_exodia) {
    this.remove_exodia = remove_exodia;
  }

  public void setRemove_ojama(Boolean remove_ojama) {
    this.remove_ojama = remove_ojama;
  }

  public void setRemove_toons(Boolean remove_toons) {
    this.remove_toons = remove_toons;
  }

  public void setUnlock_all_decks(Boolean unlock_all_decks) {
    this.unlock_all_decks = unlock_all_decks;
  }

  public void setVictory(Boolean victory) {
    this.victory = victory;
  }

  public void setAscension_level(Integer ascension_level) {
    this.ascension_level = ascension_level;
  }

  public void setCampfire_rested(Integer campfire_rested) {
    this.campfire_rested = campfire_rested;
  }

  public void setCampfire_upgraded(Integer campfire_upgraded) {
    this.campfire_upgraded = campfire_upgraded;
  }

  public void setCirclet_count(Integer circlet_count) {
    this.circlet_count = circlet_count;
  }

  public void setFloor_reached(Integer floor_reached) {
    this.floor_reached = floor_reached;
  }

  public void setGold(Integer gold) {
    this.gold = gold;
  }

  public void setHighest_max_summons(Integer highest_max_summons) {
    this.highest_max_summons = highest_max_summons;
  }

  public void setNumber_of_monsters(Integer number_of_monsters) {
    this.number_of_monsters = number_of_monsters;
  }

  public void setNumber_of_resummons(Integer number_of_resummons) {
    this.number_of_resummons = number_of_resummons;
  }

  public void setNumber_of_spells(Integer number_of_spells) {
    this.number_of_spells = number_of_spells;
  }

  public void setNumber_of_traps(Integer number_of_traps) {
    this.number_of_traps = number_of_traps;
  }

  public void setPlaytime(Integer playtime) {
    this.playtime = playtime;
  }

  public void setPurchased_purges(Integer purchased_purges) {
    this.purchased_purges = purchased_purges;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public void setTotal_synergy_tributes(Integer total_synergy_tributes) {
    this.total_synergy_tributes = total_synergy_tributes;
  }

  public void setWin_rate(Integer win_rate) {
    this.win_rate = win_rate;
  }

  public void setPlayer_experience(BigInteger player_experience) {
    this.player_experience = player_experience;
  }

  public void setSeed_source_timestamp(BigInteger seed_source_timestamp) {
    this.seed_source_timestamp = seed_source_timestamp;
  }

  public void setTimestamp(BigInteger timestamp) {
    this.timestamp = timestamp;
  }

  public void setCurrent_hp_per_floor(List<Integer> current_hp_per_floor) {
    this.current_hp_per_floor = current_hp_per_floor;
  }

  public void setGold_per_floor(List<Integer> gold_per_floor) {
    this.gold_per_floor = gold_per_floor;
  }

  public void setItem_purchase_floors(List<Integer> item_purchase_floors) {
    this.item_purchase_floors = item_purchase_floors;
  }

  public void setItems_purged_floors(List<Integer> items_purged_floors) {
    this.items_purged_floors = items_purged_floors;
  }

  public void setMax_hp_per_floor(List<Integer> max_hp_per_floor) {
    this.max_hp_per_floor = max_hp_per_floor;
  }

  public void setPotions_floor_spawned(List<Integer> potions_floor_spawned) {
    this.potions_floor_spawned = potions_floor_spawned;
  }

  public void setPotions_floor_usage(List<Integer> potions_floor_usage) {
    this.potions_floor_usage = potions_floor_usage;
  }

  public void setItems_purchased(List<String> items_purchased) {
    this.items_purchased = items_purchased;
  }

  public void setItems_purged(List<String> iterms_purged) {
    this.items_purged = iterms_purged;
  }

  public void setMaster_deck(List<String> master_deck) {
    this.master_deck = master_deck;
  }

  public void setPath_per_floor(List<String> path_per_floor) {
    this.path_per_floor = path_per_floor;
  }

  public void setPath_taken(List<String> path_taken) {
    this.path_taken = path_taken;
  }

  public void setRelics(List<String> relics) {
    this.relics = relics;
  }

  public String getBuild_version() {
    return build_version;
  }

  public String getCharacter_chosen() {
    return character_chosen;
  }

  public String getDuelistmod_version() {
    return duelistmod_version;
  }

  public String getKilled_by() {
    return killed_by;
  }

  public String getLocal_time() {
    return local_time;
  }

  public String getNeow_bonus() {
    return neow_bonus;
  }

  public String getNeow_cost() {
    return neow_cost;
  }

  public String getPlay_id() {
    return play_id;
  }

  public String getSeed_played() {
    return seed_played;
  }

  public String getStarting_deck() {
    return starting_deck;
  }

  public Boolean getAdd_base_game_cards() {
    return add_base_game_cards;
  }

  public Boolean getAllow_boosters() {
    return allow_boosters;
  }

  public Boolean getAlways_boosters() {
    return always_boosters;
  }

  public Boolean getBonus_puzzle_summons() {
    return bonus_puzzle_summons;
  }

  public Boolean getChallenge_mode() {
    return challenge_mode;
  }

  public Boolean getChose_seed() {
    return chose_seed;
  }

  public Boolean getCustomized_card_pool() {
    return customized_card_pool;
  }

  public Boolean getDuelist_curses() {
    return duelist_curses;
  }

  public Boolean getEncounter_duelist_enemies() {
    return encounter_duelist_enemies;
  }

  public Boolean getIs_ascension_mode() {
    return is_ascension_mode;
  }

  public Boolean getIs_beta() {
    return is_beta;
  }

  public Boolean getIs_daily() {
    return is_daily;
  }

  public Boolean getIs_endless() {
    return is_endless;
  }

  public Boolean getIs_prod() {
    return is_prod;
  }

  public Boolean getIs_trial() {
    return is_trial;
  }

  public Boolean getPlaying_as_kaiba() {
    return playing_as_kaiba;
  }

  public String getPool_fill() {
    return pool_fill;
  }

  public Boolean getReduced_basic() {
    return reduced_basic;
  }

  public Boolean getRemove_card_rewards() {
    return remove_card_rewards;
  }

  public Boolean getRemove_creator() {
    return remove_creator;
  }

  public Boolean getRemove_exodia() {
    return remove_exodia;
  }

  public Boolean getRemove_ojama() {
    return remove_ojama;
  }

  public Boolean getRemove_toons() {
    return remove_toons;
  }

  public Boolean getUnlock_all_decks() {
    return unlock_all_decks;
  }

  public Boolean getVictory() {
    return victory;
  }

  public Integer getAscension_level() {
    return ascension_level;
  }

  public Integer getCampfire_rested() {
    return campfire_rested;
  }

  public Integer getCampfire_upgraded() {
    return campfire_upgraded;
  }

  public Integer getCirclet_count() {
    return circlet_count;
  }

  public Integer getFloor_reached() {
    return floor_reached;
  }

  public Integer getGold() {
    return gold;
  }

  public Integer getHighest_max_summons() {
    return highest_max_summons;
  }

  public Integer getNumber_of_monsters() {
    return number_of_monsters;
  }

  public Integer getNumber_of_resummons() {
    return number_of_resummons;
  }

  public Integer getNumber_of_spells() {
    return number_of_spells;
  }

  public Integer getNumber_of_traps() {
    return number_of_traps;
  }

  public Integer getPlaytime() {
    return playtime;
  }

  public Integer getPurchased_purges() {
    return purchased_purges;
  }

  public Integer getScore() {
    return score;
  }

  public Integer getTotal_synergy_tributes() {
    return total_synergy_tributes;
  }

  public Integer getWin_rate() {
    return win_rate;
  }

  public BigInteger getPlayer_experience() {
    return player_experience;
  }

  public BigInteger getSeed_source_timestamp() {
    return seed_source_timestamp;
  }

  public BigInteger getTimestamp() {
    return timestamp;
  }

  public List<Integer> getCurrent_hp_per_floor() {
    return current_hp_per_floor;
  }

  public List<Integer> getGold_per_floor() {
    return gold_per_floor;
  }

  public List<Integer> getItem_purchase_floors() {
    return item_purchase_floors;
  }

  public List<Integer> getItems_purged_floors() {
    return items_purged_floors;
  }

  public List<Integer> getMax_hp_per_floor() {
    return max_hp_per_floor;
  }

  public List<Integer> getPotions_floor_spawned() {
    return potions_floor_spawned;
  }

  public List<Integer> getPotions_floor_usage() {
    return potions_floor_usage;
  }

  public List<String> getItems_purchased() {
    return items_purchased;
  }

  public List<String> getItems_purged() {
    return items_purged;
  }

  public List<String> getMaster_deck() {
    return master_deck;
  }

  public List<String> getPath_per_floor() {
    return path_per_floor;
  }

  public List<String> getPath_taken() {
    return path_taken;
  }

  public List<String> getRelics() {
    return relics;
  }

  public List<BossRelic> getBoss_relics() {
    return boss_relics;
  }

  public List<Event> getEvent_choices() {
    return event_choices;
  }

  public List<SpireCard> getCard_choices() {
    return card_choices;
  }

  public List<Potion> getPotions_obtained() {
    return potions_obtained;
  }

  public List<Relic> getRelics_obtained() {
    return relics_obtained;
  }

  public List<CampfireChoice> getCampfire_choices() {
    return campfire_choices;
  }

  public List<DamageInfo> getDamage_taken() {
    return damage_taken;
  }

  public TopBundle getTop() {
    return top;
  }

  public Integer getChallenge_level() {
    return challenge_level;
  }

  public void setChallenge_level(Integer challenge_level) {
    this.challenge_level = challenge_level;
  }

}
