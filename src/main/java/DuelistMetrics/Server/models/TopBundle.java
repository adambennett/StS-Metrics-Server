package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.RunLogExtensionDataDTO;
import DuelistMetrics.Server.models.dto.RunMonthDTO;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import java.math.*;

@Entity
@NamedNativeQuery(name = "getRunsByCharacterFromThisYearLookup", query = """
SELECT MONTH(from_unixtime(b.timestamp)) AS month,
       COUNT(*) AS runs
FROM bundle b
WHERE YEAR(from_unixtime(b.timestamp)) = YEAR(CURDATE()) AND
      (:character IS NULL OR b.character_chosen = :character)
GROUP BY month
""", resultSetMapping = "runMonthDtoMapping")
@SqlResultSetMapping(
        name = "runMonthDtoMapping",
        classes = @ConstructorResult(targetClass = RunMonthDTO.class,columns = {
                @ColumnResult(name = "month", type = Integer.class),
                @ColumnResult(name = "runs", type = Integer.class)})
)
@NamedNativeQuery(name = "runLogExtensionDataLookup", query = """
SELECT
    t.host,
    b.local_time AS time,
    b.unique_player_id AS uuid,
    t.time AS utcTime
FROM top_bundle t
JOIN bundle b ON b.top_id = t.event_top_id
WHERE t.host IN :hosts AND b.local_time IN :times AND (:uuid IS NULL OR b.unique_player_id = :uuid)
""", resultSetMapping = "runLogExtensionDataDtoMapping")
@SqlResultSetMapping(
        name = "runLogExtensionDataDtoMapping",
        classes = @ConstructorResult(targetClass = RunLogExtensionDataDTO.class,columns = {
                @ColumnResult(name = "host", type = String.class),
                @ColumnResult(name = "time", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "utcTime", type = String.class)
        })
)
public class TopBundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long top_id;

    private String host;
    private BigInteger time;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Bundle.class)
    @JsonIgnoreProperties("top")
    private Bundle event;

    public TopBundle() {}

    public Bundle getEvent() {
        return event;
    }
    public String getHost() {
        return host;
    }
    public void setEvent(Bundle event) {
    this.event = event;
  }

    public Long getTop_id() {
      return top_id;
    }

    public void setTop_id(Long top_id) {
      this.top_id = top_id;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public BigInteger getTime() {
      return time;
    }

    public void setTime(BigInteger time) {
      this.time = time;
    }

    @Override
    public String toString() {
        return "TopBundle{" +
                "top_id=" + top_id +
                ", host='" + host + '\'' +
                ", time=" + time +
                ", event=" + event +
                '}';
    }
}
