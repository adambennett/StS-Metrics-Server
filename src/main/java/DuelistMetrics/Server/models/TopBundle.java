package DuelistMetrics.Server.models;

import java.math.*;

public class TopBundle {

    private Bundle event;

    private String host;
    private BigInteger time;

    public TopBundle() {}

    public Bundle getEvent() {
        return event;
    }

    public void setEvent(Bundle event) {
      this.event = event;
    }

    public String getHost() {
        return host;
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
