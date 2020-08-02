package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TopInfoBundleRepo extends JpaRepository<ModInfoBundle, Long> {

    @Query(value = "SELECT modid, version FROM mod_info_bundle ORDER BY modid", nativeQuery = true)
    List<String> getAllModuleVersions();

    @Query(value = "SELECT DISTINCT modid, display_name FROM mod_info_bundle ORDER BY modid", nativeQuery = true)
    List<String> getMods();

    List<ModInfoBundle> getModInfoBundlesByModNameEquals(String modName);

    Optional<ModInfoBundle> findByModIDAndVersion(String modID, String version);
}
