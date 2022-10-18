package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TopInfoBundleRepo extends JpaRepository<ModInfoBundle, Long> {

    @Query(value = "SELECT DISTINCT modid, mod_version FROM mini_mod ORDER BY modid", nativeQuery = true)
    List<String> getAllModuleVersions();

    @Query(value = "SELECT DISTINCT modid, display_name FROM mod_info_bundle ORDER BY modid", nativeQuery = true)
    List<String> getMods();

    @Query(value = "SELECT info_bundle_id FROM duelistmetrics.mod_info_bundle WHERE is_duelist = true ORDER BY modid", nativeQuery = true)
    List<Long> getModInfoBundleIdsForAllDuelistVersions();

    @Query(value = "SELECT info_bundle_id FROM mod_info_bundle WHERE mod_name = :modName", nativeQuery = true)
    List<Long> getModInfoBundlesByModNameEquals(String modName);

    Optional<ModInfoBundle> findByModIDAndVersion(String modID, String version);

    @Query(value = "SELECT name, miba.authors FROM mod_info_bundle JOIN mod_info_bundle_authors miba on mod_info_bundle.info_bundle_id = miba.mod_info_bundle_info_bundle_id WHERE info_bundle_id = :info_id", nativeQuery = true)
    List<String> getModInfoFromInfoId(Long info_id);
}
