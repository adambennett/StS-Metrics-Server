package DuelistMetrics.Server.models.infoModels;

import DuelistMetrics.Server.models.*;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class MiniMod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mini_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("modList")
    private Bundle bundle;

    private String modID;
    private String modVersion;
    private String name;
    private String author;

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

    public String getModID() {
        return modID;
    }

    public void setModID(String ID) {
        this.modID = ID;
    }

    public String getModVersion() {
        return modVersion;
    }

    public void setModVersion(String modVersion) {
        this.modVersion = modVersion;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiniMod)) return false;
        MiniMod miniMod = (MiniMod) o;
        return Objects.equals(getModID(), miniMod.getModID()) &&
                Objects.equals(getModVersion(), miniMod.getModVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModID(), getModVersion());
    }
}
