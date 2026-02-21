package DuelistMetrics.Server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "configurations")
public class Configuration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String value;
  private Boolean active;
  private Integer threshold;
  
  @Column(name = "threshold_version")
  private Long thresholdVersion;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date")
  private Date createdDate;
}
