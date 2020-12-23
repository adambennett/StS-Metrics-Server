package DuelistMetrics.Server.models.runDetails;

import DuelistMetrics.Server.models.*;

import java.math.*;

public class RunTop {

    private String host;
    private BigInteger time;
    private DetailsBundle event;

    public RunTop() {}

    public RunTop(TopBundle transfer) {
        this.host = transfer.getHost();
        this.time = transfer.getTime();
        this.event = new DetailsBundle(transfer.getEvent());
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

    public DetailsBundle getEvent() {
        return event;
    }

    public void setEvent(DetailsBundle event) {
        this.event = event;
    }
}
