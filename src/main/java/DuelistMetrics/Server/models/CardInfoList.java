package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class CardInfoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardlist_id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "list", targetEntity = DuelistCardData.class)
    @JsonIgnoreProperties("list")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DuelistCardData> cards;

    public CardInfoList() {}

    public Integer addCardsToList(List<DuelistCardData> cards) {
        int sum = 0;
        for (DuelistCardData card : cards) {
            if (!this.cards.contains(card)) {
                card.setList(this);
                this.cards.add(card);
                sum++;
            }
        }
        return sum;
    }

    public void replaceList(List<DuelistCardData> newList) {
        this.cards = newList;
    }

    public Long getCardlist_id() {
        return cardlist_id;
    }

    public void setCardlist_id(Long card_id) {
        this.cardlist_id = card_id;
    }

    public List<DuelistCardData> getCards() {
        return cards;
    }

    public void setCards(List<DuelistCardData> cards) {
        this.cards = cards;
    }
}
