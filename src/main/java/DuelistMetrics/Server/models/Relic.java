package DuelistMetrics.Server.models;


import javax.persistence.*;


public class Relic {

    private Bundle bundle;

    private Integer floor;
    private String key;

    public Relic() {}

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
