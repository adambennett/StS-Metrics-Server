package DuelistMetrics.Server.models.builders;

import DuelistMetrics.Server.models.*;

public class DisplayCardBuilder {
    private String name;
    private String uuid;
    private String cardID;
    private Double popularity;
    private Double power;
    private Integer offered;
    private Integer picked;
    private Integer pickVic;

    public DisplayCardBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DisplayCardBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public DisplayCardBuilder setCardID(String cardID) {
        this.cardID = cardID;
        return this;
    }

    public DisplayCardBuilder setPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public DisplayCardBuilder setPower(Double power) {
        this.power = power;
        return this;
    }

    public DisplayCardBuilder setOffered(Integer offered) {
        this.offered = offered;
        return this;
    }

    public DisplayCardBuilder setPicked(Integer picked) {
        this.picked = picked;
        return this;
    }

    public DisplayCardBuilder setPickVic(Integer pickVic) {
        this.pickVic = pickVic;
        return this;
    }

    public DisplayCard createDisplayCard() {
        return new DisplayCard(name, uuid, cardID, popularity, power, offered, picked, pickVic);
    }
}
