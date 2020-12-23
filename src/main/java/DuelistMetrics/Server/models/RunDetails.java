package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.runDetails.*;

import java.util.*;

public class RunDetails {

    public RunTop top;
    private List<FloorInfo> floors;

    public RunDetails(RunTop top, List<FloorInfo> floors) {
        this.top = top;
        this.floors = floors;
    }

    public RunTop getTop() {
        return top;
    }

    public void setTop(RunTop top) {
        this.top = top;
    }

    public List<FloorInfo> getFloors() {
        return floors;
    }

    public void setFloors(List<FloorInfo> floors) {
        this.floors = floors;
    }
}
