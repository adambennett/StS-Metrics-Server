package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.*;

import java.util.*;

public class DetailsCard {

  private Integer floor;
  private String cardId;
  private String cardName;
  private List<SimpleCard> not_picked;

  public DetailsCard(SpireCard card) {
    this.floor = card.getFloor();
    this.cardId = card.getPicked();
    this.not_picked = new ArrayList<>();
    for (String s : card.getNot_picked()) {
      this.not_picked.add(new SimpleCard(s));
    }
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public List<SimpleCard> getNot_picked() {
    return not_picked;
  }

  public void setNot_picked(List<SimpleCard> not_picked) {
    this.not_picked = not_picked;
  }
}
