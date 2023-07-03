package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;


@Entity
public class CampfireChoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long fire_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("campfire_choices")
  private Bundle bundle;

  private Integer floor;
  private String data;

  @Column(name = "fire_key")
  private String key;

  public CampfireChoice() {}

  public Long getFire_id() {
    return fire_id;
  }

  public void setFire_id(Long id) {
    this.fire_id = id;
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

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
