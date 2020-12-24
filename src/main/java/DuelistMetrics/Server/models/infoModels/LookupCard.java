package DuelistMetrics.Server.models.infoModels;

import java.util.*;


public class LookupCard {

    private String card_id;
    private String name;
    private String color;
    private String rarity;
    private String type;
    private String duelistType = "";
    private String cost;
    private String text;
    private String newLineText;
    private String authors;
    private String module;
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
    private List<String> pools;

    public LookupCard(String card_id, String name, String color, String rarity, String type, String duelistType, String cost, String text, String newLineText, Integer block, Integer damage, Integer magicNumber, Integer secondMag, Integer thirdMag, Integer tributes, Integer summons, Integer entomb, Boolean isDuelistCard, List<String> pools, Integer maxUpgrades, String authors, String module) {
        this.card_id = card_id;
        this.name = name;
        this.color = color;
        this.rarity = rarity;
        this.type = type;
        this.duelistType = duelistType;
        this.cost = cost;
        this.text = text;
        this.newLineText = newLineText;
        this.block = block;
        this.damage = damage;
        this.magicNumber = magicNumber;
        this.secondMag = secondMag;
        this.thirdMag = thirdMag;
        this.tributes = tributes;
        this.summons = summons;
        this.entomb = entomb;
        this.isDuelistCard = isDuelistCard;
        this.pools = pools;
        this.maxUpgrades = maxUpgrades;
        this.module = module;
        this.authors = authors;
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

    public Boolean getDuelistCard() {
        return isDuelistCard;
    }

    public void setDuelistCard(Boolean duelistCard) {
        isDuelistCard = duelistCard;
    }

    public List<String> getPools() {
        return pools;
    }

    public void setPools(List<String> pools) {
        this.pools = pools;
    }

    public String getNewLineText() {
        return newLineText;
    }

    public void setNewLineText(String newLineText) {
        this.newLineText = newLineText;
    }

    public Integer getMaxUpgrades() {
        return maxUpgrades;
    }

    public void setMaxUpgrades(Integer maxUpgrades) {
        this.maxUpgrades = maxUpgrades;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
