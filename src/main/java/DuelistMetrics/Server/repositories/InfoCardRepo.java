package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface InfoCardRepo extends JpaRepository<InfoCard, Long> {

    @Query(value = "SELECT DISTINCT block,card_id,color,cost,damage,duelist_type,entomb,is_duelist_card,magic_number,name,rarity,second_mag,summons,third_mag,tributes,type,max_upgrades, text FROM info_card WHERE card_id = :id", nativeQuery = true)
    List<String> getCardData(String id);

    @Query(value = "SELECT DISTINCT block,card_id,color,cost,damage,duelist_type,entomb,is_duelist_card,magic_number,name,rarity,second_mag,summons,third_mag,tributes,type,max_upgrades FROM info_card WHERE card_id = :id AND info_info_bundle_id = :bundle_id", nativeQuery = true)
    List<String> getCardDataDuelist(String id, Long bundle_id);

    @Query(value = "SELECT name FROM info_card WHERE card_id = :id", nativeQuery = true)
    List<String> getCardName(String id);

    @Query(value = "SELECT name FROM info_card WHERE card_id = :id AND info_info_bundle_id = :bundle_id", nativeQuery = true)
    List<String> getCardNameDuelist(String id, Long bundle_id);

    @Query(value = "SELECT DISTINCT text FROM info_card WHERE card_id = :id AND info_info_bundle_id = :bundle_id LIMIT 1", nativeQuery = true)
    String getCardTextByIdDuelist(String id, Long bundle_id);

    @Query(value = "SELECT DISTINCT new_line_text FROM info_card WHERE card_id = :id AND info_info_bundle_id = :bundle_id LIMIT 1", nativeQuery = true)
    String getCardNewLineTextByIdDuelist(String id, Long bundle_id);

    @Query(value = "SELECT info_card_id FROM info_card WHERE card_id = :card_id AND info_info_bundle_id = :bundle_id LIMIT 1", nativeQuery = true)
    Long getInfoCardIdForPools(String card_id, Long bundle_id);

    @Query(value = "SELECT pools FROM info_card_pools INNER JOIN info_card ON info_card_pools.info_card_info_card_id = info_card.info_card_id WHERE info_card.info_card_id = :info_card_id", nativeQuery = true)
    List<String> getPoolsFromDuelistCard(Long info_card_id);

    @Query(value = "SELECT ic.card_id, icp.pools FROM info_card ic JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id WHERE (ic.info_info_bundle_id IN (:duelist_version)) AND ic.card_id LIKE '%theDuelist:%' AND icp.pools IN ('Standard Pool', 'Dragon Pool', 'Naturia Pool', 'Spellcaster Pool', 'Toon Pool', 'Zombie Pool', 'Aqua Pool', 'Fiend Pool', 'Machine Pool', 'Warrior Pool', 'Insect Pool', 'Plant Pool','Megatype Pool', 'Increment Pool', 'Creator Pool', 'Ojama Pool','Ascended I Pool', 'Ascended II Pool', 'Metronome Pool')", nativeQuery = true)
    List<String> getTrackedCardsForTierScores(List<Long> duelist_version);

    @Query(value = "SELECT ic.card_id, icp.pools FROM info_card ic JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id WHERE (ic.info_info_bundle_id IN (:duelist_version)) AND ic.card_id LIKE '%theDuelist:%' AND icp.pools IN (:poolName)", nativeQuery = true)
    List<String> getTrackedCardsForTierScores(String poolName, List<Long> duelist_version);
}
