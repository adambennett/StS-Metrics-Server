package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CardRepo extends JpaRepository<OfferCard, Long> {

  @Query("SELECT oc.name, SUM(oc.offered), SUM(oc.picked), SUM(oc.pickVic) FROM OfferCard oc LEFT JOIN PickInfo pi ON oc.info.id = pi.id WHERE pi.deck <> :notYugi GROUP BY oc.name")
  List<String> getAll(String notYugi);

  @Query("SELECT oc.name, SUM(oc.offered), SUM(oc.picked), SUM(oc.pickVic) FROM OfferCard oc LEFT JOIN PickInfo pi ON oc.info.id = pi.id WHERE pi.deck = :deck GROUP BY oc.name")
  List<String> getAllFromDeck(@Param("deck") String deck);

}
