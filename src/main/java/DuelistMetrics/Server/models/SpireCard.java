package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class SpireCard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long card_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("card_choices")
  private Bundle bundle;

  private Integer floor;
  private String picked;

  @ElementCollection
  private List<String> not_picked;

  public SpireCard() {}

  public Long getCard_id() {
    return card_id;
  }

  public void setCard_id(Long id) {
    this.card_id = id;
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
