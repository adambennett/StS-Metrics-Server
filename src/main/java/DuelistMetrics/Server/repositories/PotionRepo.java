package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PotionRepo extends JpaRepository<OfferPotion, Long> {

  @Query(name = "getPotionListLookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAll();

  @Query(name = "getPotionListFromDeckLookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAllFromDeck(@Param("deck") String deck);

}
