package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CardRepo extends JpaRepository<OfferCard, Long> {

  @Query("SELECT p FROM OfferCard p WHERE p.name = :name")
  List<OfferCard> findCardsByName(@Param("name") String name);

  @Query("SELECT SUM(offered) FROM OfferCard WHERE name = :name")
  Integer countOffer(@Param("name") String name);

  @Query("SELECT SUM(pickVic) FROM OfferCard WHERE name = :name")
  Integer countPickVic(@Param("name") String name);

  @Query("SELECT SUM(picked) FROM OfferCard WHERE name = :name")
  Integer countPicks(@Param("name") String name);

  @Query("SELECT SUM(oc.offered) FROM OfferCard oc LEFT JOIN PickInfo pi ON oc.info.id = pi.id WHERE oc.name = :name AND pi.deck = :deck")
  Integer countOfferFromDeck(@Param("name") String name, @Param("deck") String deck);

  @Query("SELECT SUM(oc.pickVic) FROM OfferCard oc LEFT JOIN PickInfo pi ON oc.info.id = pi.id WHERE oc.name = :name AND pi.deck = :deck")
  Integer countPickVicFromDeck(@Param("name") String name, @Param("deck") String deck);

  @Query("SELECT SUM(oc.picked) FROM OfferCard oc LEFT JOIN PickInfo pi ON oc.info.id = pi.id WHERE oc.name = :name AND pi.deck = :deck")
  Integer countPicksFromDeck(@Param("name") String name, @Param("deck") String deck);

  @Query("SELECT oc.name, SUM(oc.offered), SUM(oc.picked), SUM(oc.pickVic) FROM OfferCard oc GROUP BY oc.name")
  List<String> getAll();

  @Query("SELECT oc.name, SUM(oc.offered), SUM(oc.picked), SUM(oc.pickVic) FROM OfferCard oc LEFT JOIN PickInfo pi ON oc.info.id = pi.id WHERE pi.deck = :deck GROUP BY oc.name")
  List<String> getAllFromDeck(@Param("deck") String deck);

}
