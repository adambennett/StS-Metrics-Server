package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;

@Entity
@NamedNativeQuery(name = "getRelicListLookup", query = """
SELECT
    oc.name AS uuid,
    ir.name AS name,
    ir.tier AS rarity,
    ir.description_plain AS description,
    ir.flavor_text AS flavor,
    SUM(oc.picked) AS picked,
    SUM(oc.pick_vic) AS pickedVictory,
    FLOOR((SUM(oc.pick_vic)/SUM(oc.picked)) * 1000) AS power,
    IF(oc.name like 'theDuelist:%', 'Duelist', 'Base Game') AS type
FROM offer_relic oc
JOIN info_relic ir ON ir.relic_id = oc.name
JOIN pick_info_v2 pi ON pi.id = oc.infov2_id AND pi.deck != 'NotYugi'
WHERE (oc.name like 'theDuelist:%' AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)) OR oc.name in (SELECT DISTINCT relic_id FROM info_relic WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) and relic_id not like '%:%' and relic_id not like '%m_%')
GROUP BY ir.name
""", resultSetMapping = "fullInfoDisplayObjectRelicMapping")
@SqlResultSetMapping(
        name = "fullInfoDisplayObjectRelicMapping",
        classes = @ConstructorResult(targetClass = FullInfoDisplayObject.class,columns = {
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "name", type = String.class),
                @ColumnResult(name = "rarity", type = String.class),
                @ColumnResult(name = "description", type = String.class),
                @ColumnResult(name = "flavor", type = String.class),
                @ColumnResult(name = "picked", type = Integer.class),
                @ColumnResult(name = "pickedVictory", type = Integer.class),
                @ColumnResult(name = "power", type = Double.class),
                @ColumnResult(name = "type", type = String.class)})
)
@NamedNativeQuery(name = "getRelicListFromDeckLookup", query = """
SELECT
    oc.name AS uuid,
    ir.name AS name,
    ir.tier AS rarity,
    ir.description_plain AS description,
    ir.flavor_text AS flavor,
    SUM(oc.picked) AS picked,
    SUM(oc.pick_vic) AS pickedVictory,
    FLOOR((SUM(oc.pick_vic)/SUM(oc.picked)) * 1000) AS power,
    IF(oc.name like 'theDuelist:%', 'Duelist', 'Base Game') AS type
FROM offer_relic oc
LEFT JOIN pick_info_v2 pi ON oc.infov2_id = pi.id
JOIN info_relic ir ON ir.relic_id = oc.name
WHERE pi.deck = :deck AND ((oc.name like 'theDuelist:%' AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)) OR oc.name in (SELECT DISTINCT relic_id FROM info_relic WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) and relic_id not like '%:%' and relic_id not like '%m_%'))
GROUP BY ir.name
""", resultSetMapping = "fullInfoDisplayObjectRelicDeckMapping")
@SqlResultSetMapping(
        name = "fullInfoDisplayObjectRelicDeckMapping",
        classes = @ConstructorResult(targetClass = FullInfoDisplayObject.class,columns = {
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "name", type = String.class),
                @ColumnResult(name = "rarity", type = String.class),
                @ColumnResult(name = "description", type = String.class),
                @ColumnResult(name = "flavor", type = String.class),
                @ColumnResult(name = "picked", type = Integer.class),
                @ColumnResult(name = "pickedVictory", type = Integer.class),
                @ColumnResult(name = "power", type = Double.class),
                @ColumnResult(name = "type", type = String.class)})
)
public class OfferRelic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relic_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("relics")
  private PickInfo info;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("relics")
  private PickInfoV2 infoV2;

  private String name;
  private Integer picked;
  private Integer pickVic;

  public OfferRelic() {}

  public OfferRelic(String name, int picked, int pickVic, PickInfoV2 info) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.infoV2 = info;
  }

  public Long getRelic_id() {
    return relic_id;
  }

  public void setRelic_id(Long relic_id) {
    this.relic_id = relic_id;
  }

  public PickInfo getInfo() {
    return info;
  }

  public void setInfo(PickInfo info) {
    this.info = info;
  }

  public PickInfoV2 getInfoV2() {
    return infoV2;
  }

  public void setInfoV2(PickInfoV2 infoV2) {
    this.infoV2 = infoV2;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPicked() {
    return picked;
  }

  public void setPicked(Integer picked) {
    this.picked = picked;
  }

  public Integer getPickVic() {
    return pickVic;
  }

  public void setPickVic(Integer pickVic) {
    this.pickVic = pickVic;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OfferRelic)) return false;
    OfferRelic that = (OfferRelic) o;
    return Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
