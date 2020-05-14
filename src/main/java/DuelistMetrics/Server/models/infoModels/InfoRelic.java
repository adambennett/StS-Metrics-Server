package DuelistMetrics.Server.models.infoModels;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
public class InfoRelic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_relic_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("relics")
    private ModInfoBundle info;

    private String relic_id;
    private String tier;
    private String pool;
    private String name;

    @Column(length = 4028)
    private String flavorText;

    @Column(length = 4028)
    private String description;

    public InfoRelic() {}

    public Long getInfo_relic_id() {
        return info_relic_id;
    }

    public void setInfo_relic_id(Long info_relic_id) {
        this.info_relic_id = info_relic_id;
    }

    public ModInfoBundle getInfo() {
        return info;
    }

    public void setInfo(ModInfoBundle info) {
        this.info = info;
    }

    public String getRelic_id() {
        return relic_id;
    }

    public void setRelic_id(String relic_id) {
        this.relic_id = relic_id;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
