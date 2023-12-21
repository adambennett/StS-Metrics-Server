package DuelistMetrics.Server.models.infoModels;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class InfoCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_card_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cards")
    private ModInfoBundle info;

    private String card_id;
    private String name;
    private String color;
    private String rarity;
    private String type;
    private String duelistType = "";
    private String cost;

    @Column(length = 4028)
    private String text;

    @Column(length = 4028)
    private String newLineText;

    private Integer block;
    private Integer damage;
    private Integer magicNumber;
    private Integer secondMag;
    private Integer thirdMag;
    private Integer tributes;
    private Integer summons;
    private Integer entomb;
    private Integer maxUpgrades;

    private Boolean isDuelistCard;

    @ElementCollection
    private List<String> pools;

    public InfoCard() {}

    public Long getInfo_card_id() {
        return info_card_id;
    }

    public void setInfo_card_id(Long info_card_id) {
        this.info_card_id = info_card_id;
    }

    public ModInfoBundle getInfo() {
        return info;
    }

    public void setInfo(ModInfoBundle info) {
        this.info = info;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuelistType() {
        return duelistType;
    }

    public void setDuelistType(String duelistType) {
        this.duelistType = duelistType;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(Integer magicNumber) {
        this.magicNumber = magicNumber;
    }

    public Integer getSecondMag() {
        return secondMag;
    }

    public void setSecondMag(Integer secondMag) {
        this.secondMag = secondMag;
    }

    public Integer getThirdMag() {
        return thirdMag;
    }

    public void setThirdMag(Integer thirdMag) {
        this.thirdMag = thirdMag;
    }

    public Integer getTributes() {
        return tributes;
    }

    public void setTributes(Integer tributes) {
        this.tributes = tributes;
    }

    public Integer getSummons() {
        return summons;
    }

    public void setSummons(Integer summons) {
        this.summons = summons;
    }

    public Integer getEntomb() {
        return entomb;
    }

    public void setEntomb(Integer entomb) {
        this.entomb = entomb;
    }

    public Boolean getIsDuelistCard() {
        return isDuelistCard;
    }

    public void setIsDuelistCard(Boolean duelistCard) {
        isDuelistCard = duelistCard;
    }

    public List<String> getPools() {
        return pools;
    }

    public void setPools(List<String> pools) {
        this.pools = pools;
    }

    public String getNewLineText() { return newLineText; }

    public void setNewLineText(String newLineText) { this.newLineText = newLineText; }

    public Integer getMaxUpgrades() { return maxUpgrades; }

    public void setMaxUpgrades(Integer maxUpgrades) { this.maxUpgrades = maxUpgrades; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoCard)) return false;
        InfoCard infoCard = (InfoCard) o;
        return Objects.equals(getInfo(), infoCard.getInfo()) &&
                Objects.equals(getCard_id(), infoCard.getCard_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInfo(), getCard_id());
    }
}
