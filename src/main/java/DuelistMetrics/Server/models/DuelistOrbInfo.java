package DuelistMetrics.Server.models;


import DuelistMetrics.Server.models.dto.OrbInfoDTO;
import DuelistMetrics.Server.models.dto.RunMonthDTO;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

import java.util.Objects;

@Entity
@NamedNativeQuery(name = "orbInfoLookup", query = """
SELECT
    orb_id,
    name,
    passive,
    evoke,
    invert
FROM duelist_orb_info
WHERE version = :version
ORDER BY name
""", resultSetMapping = "orbInfoDtoMapping")
@SqlResultSetMapping(
        name = "orbInfoDtoMapping",
        classes = @ConstructorResult(targetClass = OrbInfoDTO.class,columns = {
                @ColumnResult(name = "orb_id", type = String.class),
                @ColumnResult(name = "name", type = String.class),
                @ColumnResult(name = "passive", type = String.class),
                @ColumnResult(name = "evoke", type = String.class),
                @ColumnResult(name = "invert", type = String.class)})
)
public class DuelistOrbInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orb_id;
    private String name;
    private String passive;
    private String evoke;
    private String invert;
    private String version;

    public DuelistOrbInfo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassive() {
        return passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }

    public String getEvoke() {
        return evoke;
    }

    public void setEvoke(String evoke) {
        this.evoke = evoke;
    }

    public String getInvert() {
        return invert;
    }

    public void setInvert(String invert) {
        this.invert = invert;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrb_id() {
        return orb_id;
    }

    public void setOrb_id(String orbId) {
        this.orb_id = orbId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DuelistOrbInfo that)) return false;
        return Objects.equals(getOrb_id(), that.getOrb_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrb_id());
    }
}
