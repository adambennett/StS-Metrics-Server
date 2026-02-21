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
@Entity(name = "failed_run")
public class FailedRun {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String upload_json;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
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

}
