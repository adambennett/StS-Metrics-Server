package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface InfoPotionRepo extends JpaRepository<InfoPotion, Long> {

    @Query(value = "SELECT DISTINCT potion_id, name FROM info_potion WHERE info_info_bundle_id IN (:info_bundle_ids)", nativeQuery = true)
    List<Object[]> potionIdMappingArchive(List<Long> info_bundle_ids);
}
