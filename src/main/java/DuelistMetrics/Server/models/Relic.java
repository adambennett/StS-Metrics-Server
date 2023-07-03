package DuelistMetrics.Server.models;


import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;


@Entity
public class Relic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relic_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("relics_obtained")
  private Bundle bundle;

  private Integer floor;

  @Column(name = "relic_key")
  private String key;

  public Relic() {}

  public Long getRelic_id() {
    return relic_id;
  }

  public void setRelic_id(Long id) {
    this.relic_id = id;
  }

  public Bundle getBundle() {
    return bundle;
  }

  public void setBundle(Bundle bundle) {
    this.bundle = bundle;
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
