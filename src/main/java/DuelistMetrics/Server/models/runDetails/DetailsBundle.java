package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.infoModels.*;

import java.math.*;
import java.util.*;

public class DetailsBundle {

  public String build_version;
  public String character_chosen;
  public String duelistmod_version;
  public String killed_by;
  public String local_time;
  public String neow_bonus;
  public String neow_cost;
  public String play_id;
  public String pool_fill;
  public String seed_played;
  public String starting_deck;
  public String country;
  public String lang;

  public Boolean add_base_game_cards;
  public Boolean allow_boosters;
  public Boolean always_boosters;
  public Boolean bonus_puzzle_summons;
  public Boolean challenge_mode;
  public Boolean chose_seed;
  public Boolean customized_card_pool;
  public Boolean duelist_curses;
  public Boolean encounter_duelist_enemies;
  public Boolean is_ascension_mode;
  public Boolean is_beta;
  public Boolean is_daily;
  public Boolean is_endless;
  public Boolean is_prod;
  public Boolean is_trial;
  public Boolean playing_as_kaiba;
  public Boolean reduced_basic;
  public Boolean remove_card_rewards;
  public Boolean remove_creator;
  public Boolean remove_exodia;
  public Boolean remove_ojama;
  public Boolean remove_toons;
  public Boolean unlock_all_decks;
  public Boolean victory;

  public Integer ascension_level;
  public Integer campfire_rested;
  public Integer campfire_upgraded;
  public Integer challenge_level;
  public Integer circlet_count;
  public Integer floor_reached;
  public Integer gold;
  public Integer highest_max_summons;
  public Integer number_of_monsters;
  public Integer number_of_resummons;
  public Integer number_of_spells;
  public Integer number_of_traps;
  public Integer playtime;
  public Integer purchased_purges;
  public Integer score;
  public Integer total_synergy_tributes;
  public Integer win_rate;

  public BigInteger player_experience;
  public BigInteger seed_source_timestamp;
  public BigInteger timestamp;

  public List<Integer> current_hp_per_floor = new ArrayList<>();
  public List<Integer> gold_per_floor = new ArrayList<>();
  public List<Integer> item_purchase_floors = new ArrayList<>();
  public List<Integer> items_purged_floors = new ArrayList<>();
  public List<Integer> max_hp_per_floor = new ArrayList<>();
  public List<Integer> potions_floor_spawned = new ArrayList<>();
  public List<Integer> potions_floor_usage = new ArrayList<>();
  public List<SimpleCard> items_purchased = new ArrayList<>();
  public List<SimpleCard> items_purged = new ArrayList<>();
  public List<SimpleCard> master_deck = new ArrayList<>();
  public List<String> path_per_floor = new ArrayList<>();
  public List<String> path_taken = new ArrayList<>();
  public List<SimpleCard> relics = new ArrayList<>();

  public List<DetailsMiniMod> modList = new ArrayList<>();
  public List<DetailsBossRelic> boss_relics = new ArrayList<>();
  public List<DetailsEvent> event_choices = new ArrayList<>();
  public List<DetailsCard> card_choices = new ArrayList<>();
  public List<DetailsPotion> potions_obtained = new ArrayList<>();
  public List<DetailsRelic> relics_obtained = new ArrayList<>();
  public List<DetailsCampfireChoice> campfire_choices = new ArrayList<>();
  public List<DamageInfo> damage_taken = new ArrayList<>();

  public DetailsBundle(Bundle transfer) {
    for (MiniMod mod : transfer.getModList()) {
      this.modList.add(new DetailsMiniMod(mod));
    }
    for (BossRelic relic : transfer.getBoss_relics()) {
      this.boss_relics.add(new DetailsBossRelic(relic));
    }
    for (Event event : transfer.getEvent_choices()) {
      this.event_choices.add(new DetailsEvent(event));
    }
    for (SpireCard card : transfer.getCard_choices()) {
      this.card_choices.add(new DetailsCard(card));
    }
    for (Potion potion : transfer.getPotions_obtained()) {
      this.potions_obtained.add(new DetailsPotion(potion));
    }
    for (Relic relic : transfer.getRelics_obtained()) {
      this.relics_obtained.add(new DetailsRelic(relic));
    }
    for (CampfireChoice choice : transfer.getCampfire_choices()) {
      this.campfire_choices.add(new DetailsCampfireChoice(choice));
    }
    this.damage_taken = transfer.getDamage_taken();
    this.build_version = transfer.getBuild_version();
    this.character_chosen = transfer.getCharacter_chosen();
    this.duelistmod_version = transfer.getDuelistmod_version();
    this.killed_by = transfer.getKilled_by();
    this.local_time = transfer.getLocal_time();
    this.neow_bonus = transfer.getNeow_bonus();
    this.neow_cost = transfer.getNeow_cost();
    this.play_id = transfer.getPlay_id();
    this.pool_fill = transfer.getPool_fill();
    this.seed_played = transfer.getSeed_played();
    this.starting_deck = transfer.getStarting_deck();
    this.country = transfer.getCountry();
    this.lang = transfer.getLang();
    this.add_base_game_cards = transfer.getAdd_base_game_cards();
    this.allow_boosters = transfer.getAllow_boosters();
    this.always_boosters = transfer.getAlways_boosters();
    this.bonus_puzzle_summons = transfer.getBonus_puzzle_summons();
    this.challenge_mode = transfer.getChallenge_mode();
    this.chose_seed = transfer.getChose_seed();
    this.customized_card_pool = transfer.getCustomized_card_pool();
    this.duelist_curses = transfer.getDuelist_curses();
    this.encounter_duelist_enemies = transfer.getEncounter_duelist_enemies();
    this.is_ascension_mode = transfer.getIs_ascension_mode();
    this.is_beta = transfer.getIs_beta();
    this.is_daily = transfer.getIs_daily();
    this.is_endless = transfer.getIs_endless();
    this.is_prod = transfer.getIs_prod();
    this.is_trial = transfer.getIs_trial();
    this.playing_as_kaiba = transfer.getPlaying_as_kaiba();
    this.reduced_basic = transfer.getReduced_basic();
    this.remove_card_rewards = transfer.getRemove_card_rewards();
    this.remove_creator = transfer.getRemove_creator();
    this.remove_exodia = transfer.getRemove_exodia();
    this.remove_ojama = transfer.getRemove_ojama();
    this.remove_toons = transfer.getRemove_toons();
    this.unlock_all_decks = transfer.getUnlock_all_decks();
    this.victory = transfer.getVictory();
    this.ascension_level = transfer.getAscension_level();
    this.campfire_rested = transfer.getCampfire_rested();
    this.campfire_upgraded = transfer.getCampfire_upgraded();
    this.challenge_level = transfer.getChallenge_level();
    this.circlet_count = transfer.getCirclet_count();
    this.floor_reached = transfer.getFloor_reached();
    this.gold = transfer.getGold();
    this.highest_max_summons = transfer.getHighest_max_summons();
    this.number_of_monsters = transfer.getNumber_of_monsters();
    this.number_of_resummons = transfer.getNumber_of_resummons();
    this.number_of_spells = transfer.getNumber_of_spells();
    this.number_of_traps = transfer.getNumber_of_traps();
    this.playtime = transfer.getPlaytime();
    this.purchased_purges = transfer.getPurchased_purges();
    this.score = transfer.getScore();
    this.total_synergy_tributes = transfer.getTotal_synergy_tributes();
    this.win_rate = transfer.getWin_rate();
    this.player_experience = transfer.getPlayer_experience();
    this.seed_source_timestamp = transfer.getSeed_source_timestamp();
    this.timestamp = transfer.getTimestamp();
    this.current_hp_per_floor = transfer.getCurrent_hp_per_floor();
    this.gold_per_floor = transfer.getGold_per_floor();
    this.item_purchase_floors = transfer.getItem_purchase_floors();
    this.items_purged_floors = transfer.getItems_purged_floors();
    this.max_hp_per_floor = transfer.getMax_hp_per_floor();
    this.potions_floor_spawned = transfer.getPotions_floor_spawned();
    this.potions_floor_usage = transfer.getPotions_floor_usage();
    this.items_purchased = new ArrayList<>();
    this.items_purged = new ArrayList<>();
    this.master_deck = new ArrayList<>();
    this.path_per_floor = transfer.getPath_per_floor();
    this.path_taken = transfer.getPath_taken();
    this.relics = new ArrayList<>();
    for (String s : transfer.getRelics()) {
      this.relics.add(new SimpleCard(s));
    }
    for (String s : transfer.getMaster_deck()) {
      this.master_deck.add(new SimpleCard(s));
    }
    for (String s : transfer.getItems_purged()) {
      this.items_purged.add(new SimpleCard(s));
    }
    for (String s : transfer.getItems_purchased()) {
      this.items_purchased.add(new SimpleCard(s));
    }

  }

  public String getBuild_version() {
    return build_version;
  }

  public void setBuild_version(String build_version) {
    this.build_version = build_version;
  }

  public String getCharacter_chosen() {
    return character_chosen;
  }

  public void setCharacter_chosen(String character_chosen) {
    this.character_chosen = character_chosen;
  }

  public String getDuelistmod_version() {
    return duelistmod_version;
  }

  public void setDuelistmod_version(String duelistmod_version) {
    this.duelistmod_version = duelistmod_version;
  }

  public String getKilled_by() {
    return killed_by;
  }

  public void setKilled_by(String killed_by) {
    this.killed_by = killed_by;
  }

  public String getLocal_time() {
    return local_time;
  }

  public void setLocal_time(String local_time) {
    this.local_time = local_time;
  }

  public String getNeow_bonus() {
    return neow_bonus;
  }

  public void setNeow_bonus(String neow_bonus) {
    this.neow_bonus = neow_bonus;
  }

  public String getNeow_cost() {
    return neow_cost;
  }

  public void setNeow_cost(String neow_cost) {
    this.neow_cost = neow_cost;
  }

  public String getPlay_id() {
    return play_id;
  }

  public void setPlay_id(String play_id) {
    this.play_id = play_id;
  }

  public String getPool_fill() {
    return pool_fill;
  }

  public void setPool_fill(String pool_fill) {
    this.pool_fill = pool_fill;
  }

  public String getSeed_played() {
    return seed_played;
  }

  public void setSeed_played(String seed_played) {
    this.seed_played = seed_played;
  }

  public String getStarting_deck() {
    return starting_deck;
  }

  public void setStarting_deck(String starting_deck) {
    this.starting_deck = starting_deck;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public Boolean getAdd_base_game_cards() {
    return add_base_game_cards;
  }

  public void setAdd_base_game_cards(Boolean add_base_game_cards) {
    this.add_base_game_cards = add_base_game_cards;
  }

  public Boolean getAllow_boosters() {
    return allow_boosters;
  }

  public void setAllow_boosters(Boolean allow_boosters) {
    this.allow_boosters = allow_boosters;
  }

  public Boolean getAlways_boosters() {
    return always_boosters;
  }

  public void setAlways_boosters(Boolean always_boosters) {
    this.always_boosters = always_boosters;
  }

  public Boolean getBonus_puzzle_summons() {
    return bonus_puzzle_summons;
  }

  public void setBonus_puzzle_summons(Boolean bonus_puzzle_summons) {
    this.bonus_puzzle_summons = bonus_puzzle_summons;
  }

  public Boolean getChallenge_mode() {
    return challenge_mode;
  }

  public void setChallenge_mode(Boolean challenge_mode) {
    this.challenge_mode = challenge_mode;
  }

  public Boolean getChose_seed() {
    return chose_seed;
  }

  public void setChose_seed(Boolean chose_seed) {
    this.chose_seed = chose_seed;
  }

  public Boolean getCustomized_card_pool() {
    return customized_card_pool;
  }

  public void setCustomized_card_pool(Boolean customized_card_pool) {
    this.customized_card_pool = customized_card_pool;
  }

  public Boolean getDuelist_curses() {
    return duelist_curses;
  }

  public void setDuelist_curses(Boolean duelist_curses) {
    this.duelist_curses = duelist_curses;
  }

  public Boolean getEncounter_duelist_enemies() {
    return encounter_duelist_enemies;
  }

  public void setEncounter_duelist_enemies(Boolean encounter_duelist_enemies) {
    this.encounter_duelist_enemies = encounter_duelist_enemies;
  }

  public Boolean getIs_ascension_mode() {
    return is_ascension_mode;
  }

  public void setIs_ascension_mode(Boolean is_ascension_mode) {
    this.is_ascension_mode = is_ascension_mode;
  }

  public Boolean getIs_beta() {
    return is_beta;
  }

  public void setIs_beta(Boolean is_beta) {
    this.is_beta = is_beta;
  }

  public Boolean getIs_daily() {
    return is_daily;
  }

  public void setIs_daily(Boolean is_daily) {
    this.is_daily = is_daily;
  }

  public Boolean getIs_endless() {
    return is_endless;
  }

  public void setIs_endless(Boolean is_endless) {
    this.is_endless = is_endless;
  }

  public Boolean getIs_prod() {
    return is_prod;
  }

  public void setIs_prod(Boolean is_prod) {
    this.is_prod = is_prod;
  }

  public Boolean getIs_trial() {
    return is_trial;
  }

  public void setIs_trial(Boolean is_trial) {
    this.is_trial = is_trial;
  }

  public Boolean getPlaying_as_kaiba() {
    return playing_as_kaiba;
  }

  public void setPlaying_as_kaiba(Boolean playing_as_kaiba) {
    this.playing_as_kaiba = playing_as_kaiba;
  }

  public Boolean getReduced_basic() {
    return reduced_basic;
  }

  public void setReduced_basic(Boolean reduced_basic) {
    this.reduced_basic = reduced_basic;
  }

  public Boolean getRemove_card_rewards() {
    return remove_card_rewards;
  }

  public void setRemove_card_rewards(Boolean remove_card_rewards) {
    this.remove_card_rewards = remove_card_rewards;
  }

  public Boolean getRemove_creator() {
    return remove_creator;
  }

  public void setRemove_creator(Boolean remove_creator) {
    this.remove_creator = remove_creator;
  }

  public Boolean getRemove_exodia() {
    return remove_exodia;
  }

  public void setRemove_exodia(Boolean remove_exodia) {
    this.remove_exodia = remove_exodia;
  }

  public Boolean getRemove_ojama() {
    return remove_ojama;
  }

  public void setRemove_ojama(Boolean remove_ojama) {
    this.remove_ojama = remove_ojama;
  }

  public Boolean getRemove_toons() {
    return remove_toons;
  }

  public void setRemove_toons(Boolean remove_toons) {
    this.remove_toons = remove_toons;
  }

  public Boolean getUnlock_all_decks() {
    return unlock_all_decks;
  }

  public void setUnlock_all_decks(Boolean unlock_all_decks) {
    this.unlock_all_decks = unlock_all_decks;
  }

  public Boolean getVictory() {
    return victory;
  }

  public void setVictory(Boolean victory) {
    this.victory = victory;
  }

  public Integer getAscension_level() {
    return ascension_level;
  }

  public void setAscension_level(Integer ascension_level) {
    this.ascension_level = ascension_level;
  }

  public Integer getCampfire_rested() {
    return campfire_rested;
  }

  public void setCampfire_rested(Integer campfire_rested) {
    this.campfire_rested = campfire_rested;
  }

  public Integer getCampfire_upgraded() {
    return campfire_upgraded;
  }

  public void setCampfire_upgraded(Integer campfire_upgraded) {
    this.campfire_upgraded = campfire_upgraded;
  }

  public Integer getChallenge_level() {
    return challenge_level;
  }

  public void setChallenge_level(Integer challenge_level) {
    this.challenge_level = challenge_level;
  }

  public Integer getCirclet_count() {
    return circlet_count;
  }

  public void setCirclet_count(Integer circlet_count) {
    this.circlet_count = circlet_count;
  }

  public Integer getFloor_reached() {
    return floor_reached;
  }

  public void setFloor_reached(Integer floor_reached) {
    this.floor_reached = floor_reached;
  }

  public Integer getGold() {
    return gold;
  }

  public void setGold(Integer gold) {
    this.gold = gold;
  }

  public Integer getHighest_max_summons() {
    return highest_max_summons;
  }

  public void setHighest_max_summons(Integer highest_max_summons) {
    this.highest_max_summons = highest_max_summons;
  }

  public Integer getNumber_of_monsters() {
    return number_of_monsters;
  }

  public void setNumber_of_monsters(Integer number_of_monsters) {
    this.number_of_monsters = number_of_monsters;
  }

  public Integer getNumber_of_resummons() {
    return number_of_resummons;
  }

  public void setNumber_of_resummons(Integer number_of_resummons) {
    this.number_of_resummons = number_of_resummons;
  }

  public Integer getNumber_of_spells() {
    return number_of_spells;
  }

  public void setNumber_of_spells(Integer number_of_spells) {
    this.number_of_spells = number_of_spells;
  }

  public Integer getNumber_of_traps() {
    return number_of_traps;
  }

  public void setNumber_of_traps(Integer number_of_traps) {
    this.number_of_traps = number_of_traps;
  }

  public Integer getPlaytime() {
    return playtime;
  }

  public void setPlaytime(Integer playtime) {
    this.playtime = playtime;
  }

  public Integer getPurchased_purges() {
    return purchased_purges;
  }

  public void setPurchased_purges(Integer purchased_purges) {
    this.purchased_purges = purchased_purges;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getTotal_synergy_tributes() {
    return total_synergy_tributes;
  }

  public void setTotal_synergy_tributes(Integer total_synergy_tributes) {
    this.total_synergy_tributes = total_synergy_tributes;
  }

  public Integer getWin_rate() {
    return win_rate;
  }

  public void setWin_rate(Integer win_rate) {
    this.win_rate = win_rate;
  }

  public BigInteger getPlayer_experience() {
    return player_experience;
  }

  public void setPlayer_experience(BigInteger player_experience) {
    this.player_experience = player_experience;
  }

  public BigInteger getSeed_source_timestamp() {
    return seed_source_timestamp;
  }

  public void setSeed_source_timestamp(BigInteger seed_source_timestamp) {
    this.seed_source_timestamp = seed_source_timestamp;
  }

  public BigInteger getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(BigInteger timestamp) {
    this.timestamp = timestamp;
  }

  public List<Integer> getCurrent_hp_per_floor() {
    return current_hp_per_floor;
  }

  public void setCurrent_hp_per_floor(List<Integer> current_hp_per_floor) {
    this.current_hp_per_floor = current_hp_per_floor;
  }

  public List<Integer> getGold_per_floor() {
    return gold_per_floor;
  }

  public void setGold_per_floor(List<Integer> gold_per_floor) {
    this.gold_per_floor = gold_per_floor;
  }

  public List<Integer> getItem_purchase_floors() {
    return item_purchase_floors;
  }

  public void setItem_purchase_floors(List<Integer> item_purchase_floors) {
    this.item_purchase_floors = item_purchase_floors;
  }

  public List<Integer> getItems_purged_floors() {
    return items_purged_floors;
  }

  public void setItems_purged_floors(List<Integer> items_purged_floors) {
    this.items_purged_floors = items_purged_floors;
  }

  public List<Integer> getMax_hp_per_floor() {
    return max_hp_per_floor;
  }

  public void setMax_hp_per_floor(List<Integer> max_hp_per_floor) {
    this.max_hp_per_floor = max_hp_per_floor;
  }

  public List<Integer> getPotions_floor_spawned() {
    return potions_floor_spawned;
  }

  public void setPotions_floor_spawned(List<Integer> potions_floor_spawned) {
    this.potions_floor_spawned = potions_floor_spawned;
  }

  public List<Integer> getPotions_floor_usage() {
    return potions_floor_usage;
  }

  public void setPotions_floor_usage(List<Integer> potions_floor_usage) {
    this.potions_floor_usage = potions_floor_usage;
  }

  public List<SimpleCard> getItems_purchased() {
    return items_purchased;
  }

  public void setItems_purchased(List<SimpleCard> items_purchased) {
    this.items_purchased = items_purchased;
  }

  public List<SimpleCard> getItems_purged() {
    return items_purged;
  }

  public void setItems_purged(List<SimpleCard> items_purged) {
    this.items_purged = items_purged;
  }

  public List<SimpleCard> getMaster_deck() {
    return master_deck;
  }

  public void setMaster_deck(List<SimpleCard> master_deck) {
    this.master_deck = master_deck;
  }

  public void setRelics(List<SimpleCard> relics) {
    this.relics = relics;
  }

  public List<String> getPath_per_floor() {
    return path_per_floor;
  }

  public void setPath_per_floor(List<String> path_per_floor) {
    this.path_per_floor = path_per_floor;
  }

  public List<String> getPath_taken() {
    return path_taken;
  }

  public void setPath_taken(List<String> path_taken) {
    this.path_taken = path_taken;
  }

  public List<DetailsMiniMod> getModList() {
    return modList;
  }

  public void setModList(List<DetailsMiniMod> modList) {
    this.modList = modList;
  }

  public List<DetailsBossRelic> getBoss_relics() {
    return boss_relics;
  }

  public List<SimpleCard> getRelics() {
    return relics;
  }

  public void setBoss_relics(List<DetailsBossRelic> boss_relics) {
    this.boss_relics = boss_relics;
  }

  public List<DetailsEvent> getEvent_choices() {
    return event_choices;
  }

  public void setEvent_choices(List<DetailsEvent> event_choices) {
    this.event_choices = event_choices;
  }

  public List<DetailsCard> getCard_choices() {
    return card_choices;
  }

  public void setCard_choices(List<DetailsCard> card_choices) {
    this.card_choices = card_choices;
  }

  public List<DetailsPotion> getPotions_obtained() {
    return potions_obtained;
  }

  public void setPotions_obtained(List<DetailsPotion> potions_obtained) {
    this.potions_obtained = potions_obtained;
  }

  public List<DetailsRelic> getRelics_obtained() {
    return relics_obtained;
  }

  public void setRelics_obtained(List<DetailsRelic> relics_obtained) {
    this.relics_obtained = relics_obtained;
  }

  public List<DetailsCampfireChoice> getCampfire_choices() {
    return campfire_choices;
  }

  public void setCampfire_choices(List<DetailsCampfireChoice> campfire_choices) {
    this.campfire_choices = campfire_choices;
  }

  public List<DamageInfo> getDamage_taken() {
    return damage_taken;
  }

  public void setDamage_taken(List<DamageInfo> damage_taken) {
    this.damage_taken = damage_taken;
  }
}
