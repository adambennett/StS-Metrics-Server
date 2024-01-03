package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface NeowRepo extends JpaRepository<OfferNeow, Long> {

  @Query(value = "SELECT oc.name, SUM(oc.picked), SUM(oc.pick_vic) FROM offer_neow oc GROUP BY oc.name", nativeQuery = true)
  List<String> getAll();

  @Query(value = "SELECT oc.name, SUM(oc.picked), SUM(oc.pick_vic) FROM offer_neow oc LEFT JOIN pick_info_v2 pi ON oc.infov2_id = pi.id WHERE pi.deck = :deck GROUP BY oc.name", nativeQuery = true)
  List<String> getAllFromDeck(@Param("deck") String deck);

}
