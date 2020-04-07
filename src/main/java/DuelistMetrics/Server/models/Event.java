package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public Event() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
