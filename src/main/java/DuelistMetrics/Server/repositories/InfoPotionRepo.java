package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface InfoPotionRepo extends JpaRepository<InfoPotion, Long> {

    @Query(value = "SELECT DISTINCT potion_id, name FROM info_potion WHERE info_info_bundle_id IN (:info_bundle_ids)", nativeQuery = true)
    List<Object[]> potionIdMappingArchive(List<Long> info_bundle_ids);

    @Query(value = "SELECT ip FROM InfoPotion ip WHERE ip.potion_id = :potionId and ip.info.info_bundle_id = :duelistModId")
    List<InfoPotion> getAllDuelistPotionDataById(String potionId, Long duelistModId);

    @Query(value = """
    SELECT
        ir.info_potion_id
    FROM offer_potion oc
    LEFT JOIN pick_info pi ON oc.info_id = pi.id
    JOIN info_potion ir ON ir.potion_id = oc.name AND ir.info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_duelist = 1)
    WHERE ir.potion_id = :potionId AND (oc.name like 'theDuelist:%' OR oc.name in (SELECT DISTINCT potion_id FROM info_potion WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) and potion_id not like '%:%' and potion_id not like '%m_%'))
    GROUP BY ir.name
    """, nativeQuery = true)
    Long getIdOfProperBaseGamePotion(String potionId);

    @Query(value = "SELECT ip FROM InfoPotion ip WHERE ip.info_potion_id = :id")
    InfoPotion getInfoPotionById(Long id);

    @Query(value = "SELECT ip FROM InfoPotion ip WHERE ip.potion_id = :potionId")
    List<InfoPotion> getAllPotionDataById(String potionId);

    @Query(value = "SELECT pools FROM info_potion_pools irp INNER JOIN info_potion ip ON irp.info_potion_info_potion_id = ip.info_potion_id WHERE ip.info_potion_id = :potionId", nativeQuery = true)
    List<String> getPoolsFromDuelistPotion(Long potionId);

    @Query(value = "SELECT info_info_bundle_id FROM info_potion WHERE potion_id = :potionId LIMIT 1", nativeQuery = true)
    Long getAnyBundleIdByPotionId(String potionId);
}
