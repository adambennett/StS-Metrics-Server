package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface InfoRelicRepo extends JpaRepository<InfoRelic, Long> {

    @Query(value = "SELECT DISTINCT relic_id, name FROM info_relic WHERE info_info_bundle_id IN (:info_bundle_ids)", nativeQuery = true)
    List<Object[]> relicIdMappingArchive(List<Long> info_bundle_ids);

    @Query(value = "SELECT ir FROM InfoRelic ir WHERE ir.relic_id = :relicId and ir.info.info_bundle_id = :duelistModId")
    List<InfoRelic> getAllDuelistRelicDataById(String relicId, Long duelistModId);

    @Query(value = """
    SELECT
        ir.info_relic_id
    FROM offer_relic oc
    LEFT JOIN pick_info pi ON oc.info_id = pi.id
    JOIN info_relic ir ON ir.relic_id = oc.name AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)
    WHERE ir.relic_id = :relicId AND (oc.name like 'theDuelist:%' OR oc.name in (SELECT DISTINCT relic_id FROM info_relic WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) and relic_id not like '%:%' and relic_id not like '%m_%'))
    GROUP BY ir.name
    """, nativeQuery = true)
    Long getIdOfProperBaseGameRelic(String relicId);

    @Query(value = "SELECT ir FROM InfoRelic ir WHERE ir.info_relic_id = :id")
    InfoRelic getInfoRelicById(Long id);

    @Query(value = "SELECT ir FROM InfoRelic ir WHERE ir.relic_id = :relicId")
    List<InfoRelic> getAllRelicDataById(String relicId);

    @Query(value = "SELECT pools FROM info_relic_pools irp INNER JOIN info_relic ir ON irp.info_relic_info_relic_id = ir.info_relic_id WHERE ir.info_relic_id = :relicId", nativeQuery = true)
    List<String> getPoolsFromDuelistRelic(Long relicId);

    @Query(value = "SELECT info_info_bundle_id FROM info_relic WHERE relic_id = :relicId LIMIT 1", nativeQuery = true)
    Long getAnyBundleIdByRelicId(String relicId);
}
