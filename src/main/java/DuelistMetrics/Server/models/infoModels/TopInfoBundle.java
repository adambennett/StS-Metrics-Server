package DuelistMetrics.Server.models.infoModels;

import java.util.List;

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
