package DuelistMetrics.Server.models.infoModels;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
public class InfoCreature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_creature_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("creatures")
    private ModInfoBundle info;

    private String name;
    private String type;
    private String creature_id;
    private Integer minHP;
    private Integer maxHP;
    private Boolean isPlayer;

    public InfoCreature() {}

    public Long getInfo_creature_id() {
        return info_creature_id;
    }

    public void setInfo_creature_id(Long info_creature_id) {
        this.info_creature_id = info_creature_id;
    }

    public ModInfoBundle getInfo() {
        return info;
    }

    public void setInfo(ModInfoBundle info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreature_id() {
        return creature_id;
    }

    public void setCreature_id(String creature_id) {
        this.creature_id = creature_id;
    }

    public Integer getMinHP() {
        return minHP;
    }

    public void setMinHP(Integer minHP) {
        this.minHP = minHP;
    }

    public Integer getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Integer maxHP) {
        this.maxHP = maxHP;
    }

    public Boolean getPlayer() {
        return isPlayer;
    }

    public void setPlayer(Boolean player) {
        isPlayer = player;
    }
}
