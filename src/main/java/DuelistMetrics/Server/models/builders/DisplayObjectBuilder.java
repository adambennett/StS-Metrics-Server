package DuelistMetrics.Server.models.builders;

import DuelistMetrics.Server.models.*;

public class DisplayObjectBuilder {
    private String name;
    private Integer picked;
    private Integer pickVic;
    private Double popularity;
    private Double power;

    public DisplayObjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DisplayObjectBuilder setPicked(Integer picked) {
        this.picked = picked;
        return this;
    }

    public DisplayObjectBuilder setPickVic(Integer pickVic) {
        this.pickVic = pickVic;
        return this;
    }

    public DisplayObjectBuilder setPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public DisplayObjectBuilder setPower(Double power) {
        this.power = power;
        return this;
    }

    public DisplayObject createDisplayObject() {
        return new DisplayObject(name, picked, pickVic, popularity, power);
    }
}
