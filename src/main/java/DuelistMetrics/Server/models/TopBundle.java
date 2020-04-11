package DuelistMetrics.Server.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.math.*;

@Entity
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
}
