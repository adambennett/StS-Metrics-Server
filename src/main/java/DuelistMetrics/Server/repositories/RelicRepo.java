package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface RelicRepo extends JpaRepository<OfferRelic, Long> {

  @Query("SELECT oc.name, SUM(oc.picked), SUM(oc.pickVic) FROM OfferRelic oc GROUP BY oc.name")
  List<String> getAll();

  @Query("SELECT oc.name, SUM(oc.picked), SUM(oc.pickVic) FROM OfferRelic oc LEFT JOIN PickInfo pi ON oc.info.id = pi.id WHERE pi.deck = :deck GROUP BY oc.name")
  List<String> getAllFromDeck(@Param("deck") String deck);

}
