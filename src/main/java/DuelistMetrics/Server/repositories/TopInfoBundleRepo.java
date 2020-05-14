package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TopInfoBundleRepo extends JpaRepository<ModInfoBundle, Long> {

    @Query(value = "SELECT mod_id, version FROM mod_info_bundle ORDER BY mod_id ASC", nativeQuery = true)
    List<String> getAllModuleVersions();
}
