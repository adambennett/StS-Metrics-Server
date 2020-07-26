package DuelistMetrics.Server.models;

import java.util.*;

public class RunDetails {

    public TopBundle top;
    public Bundle bundle;
    public List<ModViewer> modList;

    public RunDetails(TopBundle top, Bundle bundle, List<ModViewer> modList) {
        this.top = top;
        this.bundle = bundle;
        this.modList = modList;
    }
}
