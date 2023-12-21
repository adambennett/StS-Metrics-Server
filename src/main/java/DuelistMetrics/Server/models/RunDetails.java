package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.ConfigDifferenceDTO;
import DuelistMetrics.Server.models.runDetails.*;

import java.util.*;

public class RunDetails {

    public RunTop top;
    private List<FloorInfo> floors;
    private List<ConfigDifferenceDTO> configDifferenceDTOs;

    public RunDetails(RunTop top, List<FloorInfo> floors) {
        this.top = top;
        this.floors = floors;
    }

    public RunDetails(RunTop top) {
        this.top = top;
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

    public List<ConfigDifferenceDTO> getConfigDifferenceDTOs() {
        return configDifferenceDTOs;
    }

    public void setConfigDifferenceDTOs(List<ConfigDifferenceDTO> configDifferenceDTOs) {
        this.configDifferenceDTOs = configDifferenceDTOs;
    }
}
