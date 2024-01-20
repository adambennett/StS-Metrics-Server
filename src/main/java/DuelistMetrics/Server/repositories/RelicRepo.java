package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.OfferRelicV2;
import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RelicRepo extends JpaRepository<OfferRelicV2, Long> {

  @Query(name = "getRelicListV2Lookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAll(List<String> relicIds);

  @Query(name = "getRelicListFromDeckV2Lookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAllFromDeck(String deck, List<String> relicIds);

  @Query(value = """
  SELECT DISTINCT relic_id
  FROM info_relic
  WHERE info_info_bundle_id = (SELECT MAX(info_bundle_id) FROM mod_info_bundle WHERE is_base_game = 1) AND
        relic_id NOT LIKE '%:%' AND
        relic_id NOT LIKE '%m_%'
  """, nativeQuery = true)
  List<String> getRelicIdsForInfoObjectLookups();

}
