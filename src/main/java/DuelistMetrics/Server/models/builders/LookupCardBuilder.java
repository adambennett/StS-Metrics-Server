package DuelistMetrics.Server.models.builders;

import DuelistMetrics.Server.models.infoModels.*;

import java.util.*;

public class LookupCardBuilder {
    private String card_id;
    private String name;
    private String color;
    private String rarity;
    private String type;
    private String duelistType;
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

    public LookupCardBuilder setCard_id(String card_id) {
        this.card_id = card_id;
        return this;
    }

    public LookupCardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LookupCardBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public LookupCardBuilder setRarity(String rarity) {
        this.rarity = rarity;
        return this;
    }

    public LookupCardBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public LookupCardBuilder setDuelistType(String duelistType) {
        this.duelistType = duelistType;
        return this;
    }

    public LookupCardBuilder setCost(String cost) {
        this.cost = cost;
        return this;
    }

    public LookupCardBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public LookupCardBuilder setNewLineText(String newLineText) {
        this.newLineText = newLineText;
        return this;
    }

    public LookupCardBuilder setBlock(Integer block) {
        this.block = block;
        return this;
    }

    public LookupCardBuilder setDamage(Integer damage) {
        this.damage = damage;
        return this;
    }

    public LookupCardBuilder setMagicNumber(Integer magicNumber) {
        this.magicNumber = magicNumber;
        return this;
    }

    public LookupCardBuilder setSecondMag(Integer secondMag) {
        this.secondMag = secondMag;
        return this;
    }

    public LookupCardBuilder setThirdMag(Integer thirdMag) {
        this.thirdMag = thirdMag;
        return this;
    }

    public LookupCardBuilder setTributes(Integer tributes) {
        this.tributes = tributes;
        return this;
    }

    public LookupCardBuilder setSummons(Integer summons) {
        this.summons = summons;
        return this;
    }

    public LookupCardBuilder setEntomb(Integer entomb) {
        this.entomb = entomb;
        return this;
    }

    public LookupCardBuilder setIsDuelistCard(Boolean isDuelistCard) {
        this.isDuelistCard = isDuelistCard;
        return this;
    }

    public LookupCardBuilder setPools(List<String> pools) {
        this.pools = pools;
        return this;
    }

    public LookupCardBuilder setMaxUpgrades(int maxUpgrades) {
        this.maxUpgrades = maxUpgrades;
        return this;
    }

    public LookupCardBuilder setModule(String module) {
        this.module = module;
        return this;
    }

    public LookupCardBuilder setAuthors(String authors) {
        this.authors = authors;
        return this;
    }

    public LookupCard createLookupCard() {
        return new LookupCard(card_id, name, color, rarity, type, duelistType, cost, text, newLineText, block, damage, magicNumber, secondMag, thirdMag, tributes, summons, entomb, isDuelistCard, pools, maxUpgrades, authors, module);
    }
}
