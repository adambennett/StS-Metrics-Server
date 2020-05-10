package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class DuelistCardData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long card_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cards")
    private CardInfoList list;

    private String gameName;
    private String gameID;
    private String card_desc;

    private int dmg;
    private int blk;
    private int mag;
    private int secondMag;
    private int thirdMag;
    private int summons;
    private int tributes;
    private int entomb;

    public DuelistCardData() {}

    public CardInfoList getList() {
        return list;
    }

    public void setList(CardInfoList list) {
        this.list = list;
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getBlk() {
        return blk;
    }

    public void setBlk(int blk) {
        this.blk = blk;
    }

    public int getMag() {
        return mag;
    }

    public void setMag(int mag) {
        this.mag = mag;
    }

    public int getSecondMag() {
        return secondMag;
    }

    public void setSecondMag(int secondMag) {
        this.secondMag = secondMag;
    }

    public int getThirdMag() {
        return thirdMag;
    }

    public void setThirdMag(int thirdMag) {
        this.thirdMag = thirdMag;
    }

    public int getSummons() {
        return summons;
    }

    public void setSummons(int summons) {
        this.summons = summons;
    }

    public int getTributes() {
        return tributes;
    }

    public void setTributes(int tributes) {
        this.tributes = tributes;
    }

    public int getEntomb() {
        return entomb;
    }

    public void setEntomb(int entomb) {
        this.entomb = entomb;
    }

    public String getCard_desc() {
        return card_desc;
    }

    public void setCard_desc(String card_desc) {
        this.card_desc = card_desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DuelistCardData)) return false;
        DuelistCardData cardData = (DuelistCardData) o;
        return Objects.equals(getGameID(), cardData.getGameID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameID());
    }
}
