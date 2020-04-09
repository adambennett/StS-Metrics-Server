package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;


public class CampfireChoice {

    private Bundle bundle;

    private Integer floor;
    private String data;
    private String key;

    public CampfireChoice() {}

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
