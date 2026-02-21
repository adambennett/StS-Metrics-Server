package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.LeaderboardScoreWinnerDTO;
import DuelistMetrics.Server.models.dto.LeaderboardWinnerDTO;
import DuelistMetrics.Server.models.dto.PlayerNameListDTO;
import DuelistMetrics.Server.models.dto.TierBundleDTO;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.util.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.NamedNativeQuery;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import java.math.*;
import java.util.*;

@Setter
@Getter
@Entity
@NamedNativeQuery(name = "getScoreLeaderboardWinnersLookup", query = """
SELECT
    MAX(bb.duelist_score) AS score,
    bb.unique_player_id AS playerId,
    (SELECT COUNT(b.top_id) FROM bundle b WHERE b.character_chosen = 'THE_DUELIST' AND b.unique_player_id = bb.unique_player_id) AS runs
FROM bundle bb
WHERE bb.unique_player_id IS NOT NULL
GROUP BY bb.unique_player_id
ORDER BY MAX(bb.duelist_score)
LIMIT 50
""", resultSetMapping = "leaderboardWinnerDtoScoreMapping")
@SqlResultSetMapping(
        name = "leaderboardWinnerDtoScoreMapping",
        classes = @ConstructorResult(targetClass = LeaderboardScoreWinnerDTO.class,columns = {
                @ColumnResult(name = "score", type = Integer.class),
                @ColumnResult(name = "playerId", type = String.class),
                @ColumnResult(name = "runs", type = Integer.class)
        })
)
@NamedNativeQuery(name = "getTierBundlesLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc on bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL)
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getTierBundlesWithAscensionLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc on bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= :ascensionHigherThan
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getTierBundlesWithChallengeLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc on bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      challenge_level >= :challengeHigherThan
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getTierBundlesWithBothLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc on bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= :ascensionHigherThan AND
      challenge_level >= :challengeHigherThan
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getV4TierBundlesLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getV4TierBundlesWithAscensionLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= :ascensionHigherThan AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getV4TierBundlesWithChallengeLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      challenge_level >= :challengeHigherThan AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getV4TierBundlesWithBothLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= :ascensionHigherThan AND
      challenge_level >= :challengeHigherThan AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getA20TierBundlesLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= 20 AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getA20TierBundlesWithAscensionLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= 20 AND ascension_level >= :ascensionHigherThan AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getA20TierBundlesWithChallengeLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= 20 AND
      challenge_level >= :challengeHigherThan AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@NamedNativeQuery(name = "getA20TierBundlesWithBothLookup", query = """
SELECT top_id AS topId, victory, sc.picked, sc.floor, starting_deck AS startingDeck, mib.info_bundle_id AS infoBundleId
FROM bundle
JOIN spire_card sc ON bundle.top_id = sc.bundle_top_id
JOIN mod_info_bundle mib ON mib.version = TRIM(LEADING 'v' FROM bundle.duelistmod_version)
WHERE mib.is_duelist = true AND
      starting_deck = :deck AND
      sc.floor < 51 AND
      (customized_card_pool = false OR customized_card_pool IS NULL) AND
      (add_base_game_cards = false OR add_base_game_cards IS NULL) AND
      ascension_level >= 20 AND ascension_level >= :ascensionHigherThan AND
      challenge_level >= :challengeHigherThan AND
      (duelistmod_version NOT LIKE 'v1%' AND duelistmod_version NOT LIKE 'v2%' AND duelistmod_version NOT LIKE 'v3%')
""", resultSetMapping = "tierBundleDtoMapping")
@SqlResultSetMapping(
        name = "tierBundleDtoMapping",
        classes = @ConstructorResult(targetClass = TierBundleDTO.class,columns = {
                @ColumnResult(name = "topId", type = Integer.class),
                @ColumnResult(name = "victory", type = Boolean.class),
                @ColumnResult(name = "picked", type = String.class),
                @ColumnResult(name = "floor", type = Integer.class),
                @ColumnResult(name = "startingDeck", type = String.class),
                @ColumnResult(name = "infoBundleId", type = Long.class)
        })
)
@NamedNativeQuery(name = "getWinsLeaderboardWinnersLookup", query = """
SELECT
    COUNT(top_id) AS wins,
    unique_player_id AS playerId
FROM bundle
WHERE victory = 1 AND
      unique_player_id IS NOT NULL AND
      (:characterChosen IS NULL OR character_chosen = :characterChosen OR (:characterChosen = 'Non-Duelist' AND character_chosen != 'THE_DUELIST')) AND
      (:startDeck IS NULL OR starting_deck = :startDeck) AND
      (:ascension IS NULL OR ((:ascension = 20 AND ascension_level = 20) OR ascension_level >= :ascension))
GROUP BY unique_player_id
ORDER BY COUNT(top_id) DESC
LIMIT 50
""", resultSetMapping = "leaderboardWinnerDtoWinsWinnersMapping")
@SqlResultSetMapping(
        name = "leaderboardWinnerDtoWinsWinnersMapping",
        classes = @ConstructorResult(targetClass = LeaderboardWinnerDTO.class,columns = {
                @ColumnResult(name = "wins", type = Integer.class),
                @ColumnResult(name = "playerId", type = String.class)
        })
)
@NamedNativeQuery(name = "getWinsLeaderboardWinnerDataLookup", query = """
SELECT
    COUNT(top_id) AS wins,
    unique_player_id AS playerId,
    starting_deck AS startDeck
FROM bundle
WHERE victory = 1 AND
      unique_player_id IN :playerIds AND
      (:characterChosen IS NULL OR character_chosen = :characterChosen OR (:characterChosen = 'Non-Duelist' AND character_chosen != 'THE_DUELIST')) AND
      (:startDeck IS NULL OR starting_deck = :startDeck) AND
      (:ascension IS NULL OR ((:ascension = 20 AND ascension_level = 20) OR ascension_level >= :ascension))
GROUP BY unique_player_id, starting_deck
ORDER BY COUNT(top_id) DESC
""", resultSetMapping = "leaderboardWinnerDtoWinsDataMapping")
@SqlResultSetMapping(
        name = "leaderboardWinnerDtoWinsDataMapping",
        classes = @ConstructorResult(targetClass = LeaderboardWinnerDTO.class,columns = {
                @ColumnResult(name = "wins", type = Integer.class),
                @ColumnResult(name = "playerId", type = String.class),
                @ColumnResult(name = "startDeck", type = String.class)
        })
)
@NamedNativeQuery(name = "getPlayerNamesByIdsLookup", query = """
SELECT
    unique_player_id AS playerId,
    GROUP_CONCAT(DISTINCT t.host SEPARATOR ', ') AS playerNames
FROM bundle b
JOIN top_bundle t ON t.event_top_id = b.top_id
WHERE unique_player_id IN :playerIds
GROUP BY unique_player_id
""", resultSetMapping = "playerNameListDtoMapping")
@SqlResultSetMapping(
        name = "playerNameListDtoMapping",
        classes = @ConstructorResult(targetClass = PlayerNameListDTO.class,columns = {
                @ColumnResult(name = "playerId", type = String.class),
                @ColumnResult(name = "playerNames", type = String.class)
        })
)
public class Bundle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long top_id;

  @OneToOne(fetch = FetchType.EAGER, mappedBy = "event")
  @JsonIgnoreProperties("event")
  private TopBundle top;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date created_date;

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
  private String country;
  private String lang;
  private String unique_player_id;
  private String run_uuid;
  private String character_model;

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
  private Integer number_of_tributes;
  private Integer number_of_summons;
  private Integer number_of_megatype_tributes;
  private Integer number_of_spells;
  private Integer number_of_traps;
  private Integer playtime;
  private Integer purchased_purges;
  private Integer score;
  private Integer total_synergy_tributes;
  private Integer win_rate;
  private Integer duelist_score;
  private Integer duelist_score_current_version;

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
    this.created_date = event.getCreated_date();
    this.unique_player_id = event.getUnique_player_id();
    this.run_uuid = event.getRun_uuid();
    this.number_of_megatype_tributes = event.getNumber_of_megatype_tributes();
    this.number_of_summons = event.getNumber_of_summons();
    this.number_of_tributes = event.getNumber_of_tributes();
    this.duelist_score = event.getDuelist_score();
    this.duelist_score_current_version = event.getDuelist_score_current_version();
    this.character_model = event.getCharacter_model();
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

}
