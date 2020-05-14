package DuelistMetrics.Server.models.infoModels;
import com.fasterxml.jackson.annotation.*;

import java.util.*;


public class TopInfoBundle {

    private List<ModInfoBundle> info;

    public TopInfoBundle() {}

    public List<ModInfoBundle> getInfo() {
        return info;
    }

    public void setInfo(List<ModInfoBundle> info) {
        this.info = info;
    }
}
