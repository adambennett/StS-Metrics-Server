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

    @Query(value = "SELECT info_info_bundle_id FROM info_card WHERE card_id = :card_id LIMIT 1", nativeQuery = true)
    Long getAnyBundleIdByCardId(String card_id);

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

    @Query(value = "SELECT ic.card_id FROM info_card ic WHERE ic.info_info_bundle_id = :duelist_version AND ic.card_id LIKE '%theDuelist:%'", nativeQuery = true)
    List<String> getGlobalListOfTrackedCardsForTierScores(Long duelist_version);

    @Query(value = "SELECT ic.card_id, icp.pools FROM info_card ic JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id WHERE (ic.info_info_bundle_id IN (:duelist_version)) AND ic.card_id LIKE '%theDuelist:%' AND icp.pools IN ('Standard Pool',  'Standard Pool [Basic/Colorless]', 'Dragon Pool',  'Dragon Pool [Basic/Colorless]', 'Naturia Pool',  'Naturia Pool [Basic/Colorless]', 'Spellcaster Pool',  'Spellcaster Pool [Basic/Colorless]', 'Toon Pool',  'Toon Pool [Basic/Colorless]', 'Zombie Pool',  'Zombie Pool [Basic/Colorless]', 'Aqua Pool',  'Aqua Pool [Basic/Colorless]', 'Fiend Pool',  'Fiend Pool [Basic/Colorless]', 'Machine Pool', 'Machine Pool [Basic/Colorless]', 'Warrior Pool', 'Warrior Pool [Basic/Colorless]', 'Insect Pool', 'Insect Pool [Basic/Colorless]', 'Plant Pool', 'Plant Pool [Basic/Colorless]', 'Megatype Pool', 'Megatype Pool [Basic/Colorless]', 'Beast Pool', 'Beast Pool [Basic/Colorless]', 'Increment Pool', 'Increment Pool [Basic/Colorless]', 'Creator Pool', 'Creator Pool [Basic/Colorless]', 'Ojama Pool', 'Ojama Pool [Basic/Colorless]', 'Ascended I Pool', 'Ascended I Pool [Basic/Colorless]', 'Ascended II Pool',  'Ascended II Pool [Basic/Colorless]',  'Metronome Pool', 'Metronome Pool [Basic/Colorless]')", nativeQuery = true)
    List<String> getTrackedCardsForTierScores(List<Long> duelist_version);

    @Query(value = "SELECT ic.card_id, icp.pools FROM info_card ic JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id WHERE (ic.info_info_bundle_id IN (:duelist_version)) AND ic.card_id LIKE '%theDuelist:%' AND icp.pools IN (:pool_name, :pool_basic)", nativeQuery = true)
    List<String> getTrackedCardsForTierScores(String pool_name, List<Long> duelist_version, String pool_basic);

    @Query(value = "SELECT DISTINCT card_id, name FROM info_card WHERE info_info_bundle_id IN (:info_bundle_ids) GROUP BY card_id", nativeQuery = true)
    List<Object[]> cardIdMappingArchive(List<Long> info_bundle_ids);

    @Query(value = "SELECT card_id, name FROM info_card WHERE info_info_bundle_id = :duelist_version AND card_id IN (:card_ids)", nativeQuery = true)
    Map<String, String> lookupDuelistCardNames(List<String> card_ids, Long duelist_version);

    @Query(value = "SELECT DISTINCT card_id, name FROM info_card JOIN info_card_pools icp on info_card.info_card_id = icp.info_card_info_card_id WHERE icp.pools = :pool OR icp.pools = :pool_basic", nativeQuery = true)
    List<String> getDuelistCardsFromPool(String pool, String pool_basic);

    @Query(value = "SELECT " +
            "sc.pool_name as scorePool, icp.pools as infoPool, ic.block as block, ic.card_id as cardId, ic.color as color, ic.cost as cost, ic.damage as damage, ic.duelist_type as duelistType, " +
            "ic.entomb as entomb, ic.magic_number as magicNumber, ic.name as name, ic.rarity as rarity, ic.second_mag as secondMagic, ic.summons as summons, " +
            "ic.text as text, ic.third_mag as thirdMagic, ic.tributes as tributes, ic.type as type, ic.new_line_text as formattedText, ic.max_upgrades as maxUpgrades, " +
            "sc.act0_score as act0score, sc.act1_score as act1score, sc.act2_score as act2Score, sc.act3_score as act3Score, sc.overall_score as overallScore, sc.last_updated as scoreLastUpdated " +
            "FROM info_card ic " +
            "JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id " +
            "LEFT JOIN scored_card sc on ic.card_id = sc.card_id and sc.pool_name = :poolName " +
            "WHERE ic.info_info_bundle_id = :version and (icp.pools = :poolName or icp.pools = CONCAT(:poolName, ' [Basic/Colorless]')) ORDER BY scorePool, cardId", nativeQuery = true)
    List<Object[]> getDuelistCardsByPool(String poolName, long version);

    @Query(value = "SELECT " +
            "sc.pool_name as scorePool, icp.pools as infoPool, ic.block as block, ic.card_id as cardId, ic.color as color, ic.cost as cost, ic.damage as damage, ic.duelist_type as duelistType, " +
            "ic.entomb as entomb, ic.magic_number as magicNumber, ic.name as name, ic.rarity as rarity, ic.second_mag as secondMagic, ic.summons as summons, " +
            "ic.text as text, ic.third_mag as thirdMagic, ic.tributes as tributes, ic.type as type, ic.new_line_text as formattedText, ic.max_upgrades as maxUpgrades, " +
            "sc.act0_score as act0score, sc.act1_score as act1score, sc.act2_score as act2Score, sc.act3_score as act3Score, sc.overall_score as overallScore, sc.last_updated as scoreLastUpdated " +
            "FROM info_card ic " +
            "JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id " +
            "LEFT JOIN scored_card sc on ic.card_id = sc.card_id " +
            "WHERE ic.info_info_bundle_id = :version and (sc.pool_name = icp.pools or (substring_index(sc.pool_name, ' ', 2) = substring_index(icp.pools, ' ', 2) and icp.pools not like '% Deck%')) " +
            "ORDER BY scorePool, cardId", nativeQuery = true)
    List<Object[]> getAllDuelistCards(long version);

    @Query(value = "SELECT DISTINCT " +
            "icp.pools as pool " +
            "FROM info_card ic " +
            "JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id " +
            "WHERE icp.pools != 'Unknown' and icp.pools like '%Pool%' and ic.card_id = :cardId", nativeQuery = true)
    List<String> getAllPoolsForCard(String cardId);

    @Query(value = "SELECT DISTINCT " +
            "icp.pools as pool, ic.card_id as cardId " +
            "FROM info_card ic " +
            "JOIN info_card_pools icp on ic.info_card_id = icp.info_card_info_card_id " +
            "WHERE icp.pools != 'Unknown' and icp.pools like '%Pool%' and ic.card_id in :cardIds", nativeQuery = true)
    List<String[]> getAllPoolsForCards(List<String> cardIds);
}
