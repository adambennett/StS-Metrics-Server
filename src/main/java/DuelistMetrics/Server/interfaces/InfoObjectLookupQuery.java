package DuelistMetrics.Server.interfaces;

import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;

import java.util.List;

@FunctionalInterface
public interface InfoObjectLookupQuery {

    List<FullInfoDisplayObject> getInfoData(List<String> objectIds);

}
