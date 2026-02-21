package DuelistMetrics.Server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity(name = "bundle_config_difference_xref")
public class BundleConfigDifferenceXREF {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long bundle_id;
  private Long difference_id;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date created_date;

  public BundleConfigDifferenceXREF() {}

  public BundleConfigDifferenceXREF(Long id, Long bundle_id, Long difference_id, Date created_date) {
    this.id = id;
    this.bundle_id = bundle_id;
    this.difference_id = difference_id;
    this.created_date = created_date;
  }

}
