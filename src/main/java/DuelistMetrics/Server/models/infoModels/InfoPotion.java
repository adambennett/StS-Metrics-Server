package DuelistMetrics.Server.models.infoModels;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class InfoPotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_potion_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("potions")
    private ModInfoBundle info;

    private String potion_id;
    private String name;
    private String rarity;

    @Column(length = 4028)
    private String description;

    @Column(length = 4028)
    private String descriptionPlain;

    private String playerClass;

    @ElementCollection
    private List<String> pools;

    public InfoPotion() {}

    public Long getInfo_potion_id() {
        return info_potion_id;
    }

    public void setInfo_potion_id(Long info_potion_id) {
        this.info_potion_id = info_potion_id;
    }

    public ModInfoBundle getInfo() {
        return info;
    }

    public void setInfo(ModInfoBundle info) {
        this.info = info;
    }

    public String getPotion_id() {
        return potion_id;
    }

    public void setPotion_id(String potion_id) {
        this.potion_id = potion_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionPlain() {
        return descriptionPlain;
    }

    public void setDescriptionPlain(String descriptionPlain) {
        this.descriptionPlain = descriptionPlain;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public List<String> getPools() {
        return pools;
    }

    public void setPools(List<String> pools) {
        this.pools = pools;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoPotion)) return false;
        InfoPotion that = (InfoPotion) o;
        return Objects.equals(getInfo(), that.getInfo()) &&
                Objects.equals(getPotion_id(), that.getPotion_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInfo(), getPotion_id());
    }
}
