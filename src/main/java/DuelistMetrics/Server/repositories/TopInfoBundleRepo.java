package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TopInfoBundleRepo extends JpaRepository<ModInfoBundle, Long> {

    @Query(value = "SELECT modid, version FROM mod_info_bundle ORDER BY modid", nativeQuery = true)
    List<String> getAllModuleVersions();

    Optional<ModInfoBundle> findByModIDAndVersion(String modID, String version);
}
