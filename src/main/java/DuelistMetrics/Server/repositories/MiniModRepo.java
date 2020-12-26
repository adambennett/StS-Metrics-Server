package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface MiniModRepo extends JpaRepository<MiniMod, Long> {

    @Query(value = "SELECT * FROM mini_mod WHERE bundle_top_id = :id", nativeQuery = true)
    List<MiniMod> getByBundleId(Long id);

    @Query(value = "SELECT info_bundle_id FROM mod_info_bundle WHERE modid = :mod_id and version = :version", nativeQuery = true)
    Long getBundleId(String mod_id, String version);

}
