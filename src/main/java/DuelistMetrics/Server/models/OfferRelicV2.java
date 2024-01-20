package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "offer_relic_v2")
@NamedNativeQuery(name = "getRelicListV2Lookup", query = """
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
FROM offer_relic_v2 oc
JOIN info_relic ir ON ir.relic_id = oc.name
JOIN pick_info_v2 pi ON pi.id = oc.infov2_id AND pi.deck != 'NotYugi'
WHERE (oc.name like 'theDuelist:%' AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)) OR oc.name in :relicIds
GROUP BY ir.name
""", resultSetMapping = "v2fullInfoDisplayObjectRelicMapping")
@SqlResultSetMapping(
        name = "v2fullInfoDisplayObjectRelicMapping",
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
@NamedNativeQuery(name = "getRelicListFromDeckV2Lookup", query = """
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
FROM offer_relic_v2 oc
LEFT JOIN pick_info_v2 pi ON oc.infov2_id = pi.id
JOIN info_relic ir ON ir.relic_id = oc.name
WHERE pi.deck = :deck AND ((oc.name like 'theDuelist:%' AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)) OR oc.name in :relicIds)
GROUP BY ir.name
""", resultSetMapping = "v2fullInfoDisplayObjectRelicDeckMapping")
@SqlResultSetMapping(
        name = "v2fullInfoDisplayObjectRelicDeckMapping",
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
public class OfferRelicV2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long relic_id;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("relics")
  private PickInfoV2 infoV2;

  private String name;
  private Integer picked;
  private Integer pickVic;

  public OfferRelicV2() {}

  public OfferRelicV2(String name, int picked, int pickVic, PickInfoV2 info) {
    this.name = name;
    this.picked = picked;
    this.pickVic = pickVic;
    this.infoV2 = info;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OfferRelicV2 that)) return false;
    return Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
