package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;

@Entity
@NamedNativeQuery(name = "getPotionListLookup", query = """
SELECT
    oc.name AS uuid,
    ir.name AS name,
    ir.rarity AS rarity,
    ir.description_plain AS description,
    '' AS flavor,
    SUM(oc.picked) AS picked,
    SUM(oc.pick_vic) AS pickedVictory,
    FLOOR((SUM(oc.pick_vic)/SUM(oc.picked)) * 1000) AS power,
    IF(oc.name like 'theDuelist:%', 'Duelist', 'Base Game') AS type
FROM offer_potion oc
JOIN info_potion ir ON ir.potion_id = oc.name AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)
JOIN pick_info pi ON pi.id = oc.info_id AND pi.deck != 'NotYugi'
WHERE oc.name like 'theDuelist:%' OR oc.name in (SELECT DISTINCT potion_id FROM info_potion WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) and potion_id not like '%:%' and potion_id not like '%m_%')
GROUP BY ir.name
""", resultSetMapping = "fullInfoDisplayObjectPotionMapping")
@SqlResultSetMapping(
        name = "fullInfoDisplayObjectPotionMapping",
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
@NamedNativeQuery(name = "getPotionListFromDeckLookup", query = """
SELECT
    oc.name AS uuid,
    ir.name AS name,
    ir.rarity AS rarity,
    ir.description_plain AS description,
    '' AS flavor,
    SUM(oc.picked) AS picked,
    SUM(oc.pick_vic) AS pickedVictory,
    FLOOR((SUM(oc.pick_vic)/SUM(oc.picked)) * 1000) AS power,
    IF(oc.name like 'theDuelist:%', 'Duelist', 'Base Game') AS type
FROM offer_potion oc
LEFT JOIN pick_info pi ON oc.info_id = pi.id
JOIN info_potion ir ON ir.potion_id = oc.name AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)
WHERE pi.deck = :deck AND (oc.name like 'theDuelist:%' OR oc.name in (SELECT DISTINCT potion_id FROM info_potion WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) and potion_id not like '%:%' and potion_id not like '%m_%'))
GROUP BY ir.name
""", resultSetMapping = "fullInfoDisplayObjectPotionDeckMapping")
@SqlResultSetMapping(
        name = "fullInfoDisplayObjectPotionDeckMapping",
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
public class OfferPotion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long potion_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("potions")
  private PickInfo info;

  private String name;
  private Integer picked;
  private Integer pickVic;

  public OfferPotion() {}

  public OfferPotion(String name, int picked, int pickVic, PickInfo info) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.info = info;
  }

  public Long getPotion_id() {
    return potion_id;
  }

  public void setPotion_id(Long relic_id) {
    this.potion_id = relic_id;
  }

  public PickInfo getInfo() {
    return info;
  }

  public void setInfo(PickInfo info) {
    this.info = info;
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
    if (!(o instanceof OfferPotion)) return false;
    OfferPotion that = (OfferPotion) o;
    return Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
