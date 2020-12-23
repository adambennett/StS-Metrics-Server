package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface InfoRelicRepo extends JpaRepository<InfoRelic, Long> {

    @Query(value = "SELECT DISTINCT relic_id, name FROM info_relic WHERE info_info_bundle_id IN (:info_bundle_ids)", nativeQuery = true)
    List<String> relicIdMappingArchive(List<Long> info_bundle_ids);
}
