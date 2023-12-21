package DuelistMetrics.Server.models.infoModels;

import DuelistMetrics.Server.models.dto.KeywordDTO;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.util.*;

@Entity
@NamedNativeQuery(name = "getDuelistKeywordListLookup", query = """
SELECT DISTINCT
  CONCAT(UCASE(LEFT(ik.name, 1)), SUBSTRING(ik.name, 2)) AS keyword,
  ik.description_plain AS description,
  GROUP_CONCAT(DISTINCT CONCAT(UCASE(LEFT(ikn.names, 1)), SUBSTRING(ikn.names, 2)) SEPARATOR ', ') AS allNames
FROM info_keyword ik
LEFT JOIN info_keyword_names ikn ON ikn.info_keyword_info_keyword_id = ik.info_keyword_id
WHERE ik.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1) AND
  ik.name NOT IN (
  '[B]', '[E]', '[G]', '[R]', '[W]', 'artifact', 'autoplay', 'backfire', 'block', 'burn', 'calm', 'channel', 'confused',
  'critical', 'curse', 'dark', 'dazed', 'de-energized', 'decrepit', 'dexterity', 'divinity', 'ethereal', 'evoke', 'exhaust',
  'fatal', 'focus', 'frail', 'frost', 'golem''s might', 'hubris:piercing', 'innate', 'intangible', 'lightning', 'lock-on',
  'locked', 'manaspark', 'mantra' ,'minion', 'mitigation', 'necrotic', 'opener', 'plasma', 'poison', 'reflection',
  'retain', 'ritual', 'scry', 'shielding', 'shiv', 'shredded', 'slothful', 'snecko', 'stance', 'startup', 'status',
  'strength', 'strike', 'thorns', 'transform', 'unknown', 'unplayable', 'upgrade', 'vulnerable', 'weak', 'wound', 'wrath',
  'resummon'
)
GROUP BY ik.name
ORDER BY ik.name
""", resultSetMapping = "duelistKeywordDtoMapping")
@SqlResultSetMapping(
        name = "duelistKeywordDtoMapping",
        classes = @ConstructorResult(targetClass = KeywordDTO.class,columns = {
                @ColumnResult(name = "keyword", type = String.class),
                @ColumnResult(name = "description", type = String.class),
                @ColumnResult(name = "allNames", type = String.class)
        })
)
public class InfoKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_keyword_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("keywords")
    private ModInfoBundle info;

    @ElementCollection
    private List<String> names;

    @Column(length = 4028)
    private String description;
    @Column(length = 4028)
    private String descriptionPlain;

    private String name;

    public InfoKeyword() {}

    public Long getInfo_keyword_id() {
        return info_keyword_id;
    }

    public void setInfo_keyword_id(Long info_keyword_id) {
        this.info_keyword_id = info_keyword_id;
    }

    public ModInfoBundle getInfo() {
        return info;
    }

    public void setInfo(ModInfoBundle info) {
        this.info = info;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoKeyword)) return false;
        InfoKeyword that = (InfoKeyword) o;
        return Objects.equals(getInfo(), that.getInfo()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInfo(), getName());
    }
}
