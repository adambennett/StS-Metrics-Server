package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface InfoCardRepo extends JpaRepository<InfoCard, Long> {

    @Query(value = "SELECT DISTINCT block,card_id,color,cost,damage,duelist_type,entomb,is_duelist_card,magic_number,name,rarity,second_mag,summons,third_mag,tributes,type,text FROM info_card WHERE card_id = :id", nativeQuery = true)
    List<String> getCardData(String id);

    @Query(value = "SELECT DISTINCT block,card_id,color,cost,damage,duelist_type,entomb,is_duelist_card,magic_number,name,rarity,second_mag,summons,third_mag,tributes,type FROM info_card WHERE card_id = :id AND info_info_bundle_id = :bundle_id", nativeQuery = true)
    List<String> getCardDataDuelist(String id, Long bundle_id);

    @Query(value = "SELECT DISTINCT text FROM info_card WHERE card_id = :id AND info_info_bundle_id = :bundle_id LIMIT 1", nativeQuery = true)
    String getCardTextByIdDuelist(String id, Long bundle_id);

    @Query(value = "SELECT DISTINCT new_line_text FROM info_card WHERE card_id = :id AND info_info_bundle_id = :bundle_id LIMIT 1", nativeQuery = true)
    String getCardNewLineTextByIdDuelist(String id, Long bundle_id);

    @Query(value = "SELECT info_card_id FROM info_card WHERE card_id = :card_id AND info_info_bundle_id = :bundle_id LIMIT 1", nativeQuery = true)
    Long getInfoCardIdForPools(String card_id, Long bundle_id);

    @Query(value = "SELECT pools FROM info_card_pools INNER JOIN info_card ON info_card_pools.info_card_info_card_id = info_card.info_card_id WHERE info_card.info_card_id = :info_card_id", nativeQuery = true)
    List<String> getPoolsFromDuelistCard(Long info_card_id);
}
