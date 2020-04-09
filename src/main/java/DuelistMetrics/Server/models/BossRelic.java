package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;


public class BossRelic {

    private Bundle bundle;
    private String picked;
    private List<String> not_picked;

    public BossRelic() {}

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getPicked() {
        return picked;
    }

    public void setPicked(String picked) {
        this.picked = picked;
    }

    public List<String> getNot_picked() {
        return not_picked;
    }

    public void setNot_picked(List<String> not_picked) {
        this.not_picked = not_picked;
    }
}
