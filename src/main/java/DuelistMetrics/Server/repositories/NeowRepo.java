package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.OfferNeowV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeowRepo extends JpaRepository<OfferNeowV2, Long> {

  @Query(value = "SELECT oc.name, SUM(oc.picked), SUM(oc.pick_vic) FROM offer_neow_v2 oc GROUP BY oc.name", nativeQuery = true)
  List<String> getAll();

  @Query(value = "SELECT oc.name, SUM(oc.picked), SUM(oc.pick_vic) FROM offer_neow_v2 oc LEFT JOIN pick_info_v2 pi ON oc.infov2_id = pi.id WHERE pi.deck = :deck GROUP BY oc.name", nativeQuery = true)
  List<String> getAllFromDeck(@Param("deck") String deck);

}
