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

@Entity(name = "failed_run")
public class FailedRun {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String upload_json;

  @Temporal(TemporalType.TIMESTAMP)
  @Generated(GenerationTime.INSERT)
  private Date created_date;

  public FailedRun() {}

  public FailedRun(String upload_json) {
    this(null, upload_json, null);
  }

  public FailedRun(Long id, String upload_json, Date created_date) {
    this.id = id;
    this.upload_json = upload_json;
    this.created_date = created_date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUpload_json() {
    return upload_json;
  }

  public void setUpload_json(String upload_json) {
    this.upload_json = upload_json;
  }

  public Date getCreated_date() {
    return created_date;
  }

  public void setCreated_date(Date created_date) {
    this.created_date = created_date;
  }
}
