package DuelistMetrics.Server.models.infoModels;

import DuelistMetrics.Server.models.*;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
public class MiniMod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mini_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("modList")
    private Bundle bundle;

    private String ID;
    private String modVersion;
    private String name;

    public MiniMod() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMini_id() {
        return mini_id;
    }

    public void setMini_id(Long mini_id) {
        this.mini_id = mini_id;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getModVersion() {
        return modVersion;
    }

    public void setModVersion(String modVersion) {
        this.modVersion = modVersion;
    }
}
