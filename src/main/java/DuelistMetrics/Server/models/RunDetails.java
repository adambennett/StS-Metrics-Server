package DuelistMetrics.Server.models;

import java.util.*;

public class RunDetails {

    public TopBundle top;
    public List<FloorInfo> floors;

    public RunDetails(TopBundle top, List<FloorInfo> floors) {
        this.top = top;
        this.floors = floors;
    }
}
