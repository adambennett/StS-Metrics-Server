package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;


public class Potion {

    private Bundle bundle;

    private Integer floor;
    private String key;

    public Potion() {}

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
