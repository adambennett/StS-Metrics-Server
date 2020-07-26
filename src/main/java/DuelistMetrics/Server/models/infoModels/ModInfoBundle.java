package DuelistMetrics.Server.models.infoModels;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class ModInfoBundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_bundle_id;

    private String modID;
    private String name;
    private String modName;
    private String displayName;
    private String version;
    private Boolean isDuelist;
    private Boolean isBaseGame;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = InfoCard.class)
    @JsonIgnoreProperties("info")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InfoCard> cards;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = InfoRelic.class)
    @JsonIgnoreProperties("info")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InfoRelic> relics;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = InfoCreature.class)
    @JsonIgnoreProperties("info")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InfoCreature> creatures;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = InfoPotion.class)
    @JsonIgnoreProperties("info")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InfoPotion> potions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "info", targetEntity = InfoKeyword.class)
    @JsonIgnoreProperties("info")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InfoKeyword> keywords;

    public ModInfoBundle() {}

    public Boolean getIsDuelist() {
        return isDuelist;
    }

    public void setIsDuelist(Boolean duelist) {
        isDuelist = duelist;
    }

    public Boolean getIsBaseGame() {
        return isBaseGame;
    }

    public void setIsBaseGame(Boolean baseGame) {
        isBaseGame = baseGame;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getInfo_bundle_id() {
        return info_bundle_id;
    }

    public void setInfo_bundle_id(Long info_bundle_id) {
        this.info_bundle_id = info_bundle_id;
    }

    public String getModID() {
        return modID;
    }

    public void setModID(String mod_id) {
        this.modID = mod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public List<InfoCard> getCards() {
        return cards;
    }

    public void setCards(List<InfoCard> cards) {
        this.cards = cards;
    }

    public List<InfoRelic> getRelics() {
        return relics;
    }

    public void setRelics(List<InfoRelic> relics) {
        this.relics = relics;
    }

    public List<InfoCreature> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<InfoCreature> creatures) {
        this.creatures = creatures;
    }

    public List<InfoPotion> getPotions() {
        return potions;
    }

    public void setPotions(List<InfoPotion> potions) {
        this.potions = potions;
    }

    public List<InfoKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<InfoKeyword> keywords) {
        this.keywords = keywords;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModInfoBundle)) return false;
        ModInfoBundle that = (ModInfoBundle) o;
        return Objects.equals(getModID(), that.getModID()) &&
                Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModID(), getVersion());
    }
}
