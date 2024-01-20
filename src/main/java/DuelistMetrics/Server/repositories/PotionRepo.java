package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.OfferPotionV2;
import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotionRepo extends JpaRepository<OfferPotionV2, Long> {

  @Query(name = "getPotionListV2Lookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAll(List<String> potionIds);

  @Query(name = "getPotionListFromDeckV2Lookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAllFromDeck(String deck, List<String> potionIds);

  @Query(value = """
  SELECT DISTINCT potion_id
  FROM info_potion
  WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) AND
        potion_id NOT LIKE '%:%' AND
        potion_id NOT LIKE '%m_%'
  """, nativeQuery = true)
  List<String> getPotionIdsForInfoObjectLookups();

}
