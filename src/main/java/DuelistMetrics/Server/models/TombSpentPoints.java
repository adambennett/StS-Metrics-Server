package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;

@Entity
public class TombSpentPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tomb_event_id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "spent_points")
    @JsonIgnoreProperties("spent_points")
    private Event tomb;

    private Integer magic;
    private Integer greed;
    private Integer war;
    private Integer power;
    private Integer hunger;

    public TombSpentPoints() {}

    public Long getTomb_event_id() {
        return tomb_event_id;
    }

    public void setTomb_event_id(Long tomb_event_id) {
        this.tomb_event_id = tomb_event_id;
    }

    public Event getTomb() {
        return tomb;
    }

    public void setTomb(Event tomb) {
        this.tomb = tomb;
    }

    public Integer getMagic() {
        return magic;
    }

    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    public Integer getGreed() {
        return greed;
    }

    public void setGreed(Integer greed) {
        this.greed = greed;
    }

    public Integer getWar() {
        return war;
    }

    public void setWar(Integer war) {
        this.war = war;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getHunger() {
        return hunger;
    }

    public void setHunger(Integer hunger) {
        this.hunger = hunger;
    }

    @Override
    public String toString() {
        return "TombSpentPoints{" +
                "tomb_event_id=" + tomb_event_id +
                ", tomb=" + tomb +
                ", magic=" + magic +
                ", greed=" + greed +
                ", war=" + war +
                ", power=" + power +
                ", hunger=" + hunger +
                '}';
    }
}
