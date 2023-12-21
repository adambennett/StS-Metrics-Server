package DuelistMetrics.Server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.Date;

@Entity(name = "bundle_config_difference_xref")
public class BundleConfigDifferenceXREF {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long bundle_id;
  private Long difference_id;

  @Temporal(TemporalType.TIMESTAMP)
  @Generated(GenerationTime.INSERT)
  private Date created_date;

  public BundleConfigDifferenceXREF() {}

  public BundleConfigDifferenceXREF(Long id, Long bundle_id, Long difference_id, Date created_date) {
    this.id = id;
    this.bundle_id = bundle_id;
    this.difference_id = difference_id;
    this.created_date = created_date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBundle_id() {
    return bundle_id;
  }

  public void setBundle_id(Long bundleId) {
    this.bundle_id = bundleId;
  }

  public Long getDifference_id() {
    return difference_id;
  }

  public void setDifference_id(Long differenceId) {
    this.difference_id = differenceId;
  }

  public Date getCreated_date() {
    return created_date;
  }

  public void setCreated_date(Date createdDate) {
    this.created_date = createdDate;
  }
}
