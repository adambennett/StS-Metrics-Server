package DuelistMetrics.Server.models;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
public class DamageInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long damage_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("damage_taken")
  private Bundle bundle;

  private Integer damage;
  private Integer floor;
  private Integer turns;
  private String enemies;

  public DamageInfo() {}

  public Long getDamage_id() {
    return damage_id;
  }

  public void setDamage_id(Long id) {
    this.damage_id = id;
  }

  public Bundle getBundle() {
    return bundle;
  }

  public void setBundle(Bundle bundle) {
    this.bundle = bundle;
  }

  public Integer getDamage() {
    return damage;
  }

  public void setDamage(Integer damage) {
    this.damage = damage;
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public Integer getTurns() {
    return turns;
  }

  public void setTurns(Integer turns) {
    this.turns = turns;
  }

  public String getEnemies() {
    return enemies;
  }

  public void setEnemies(String enemies) {
    this.enemies = enemies;
  }
}
