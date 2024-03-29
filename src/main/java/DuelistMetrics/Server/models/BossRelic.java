package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;


@Entity
public class BossRelic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boss_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("boss_relics")
  private Bundle bundle;

  private String picked;

  @ElementCollection
  private List<String> not_picked;

  public BossRelic() {}

  public Long getBoss_id() {
    return boss_id;
  }

  public void setBoss_id(Long id) {
    this.boss_id = id;
  }

  public Bundle getBundle() {
    return bundle;
  }

  public void setBundle(Bundle bundle) {
    this.bundle = bundle;
  }

  public String getPicked() {
    return picked;
  }

  public void setPicked(String picked) {
    this.picked = picked;
  }

  public List<String> getNot_picked() {
    return not_picked;
  }

  public void setNot_picked(List<String> not_picked) {
    this.not_picked = not_picked;
  }
}
