package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface RelicRepo extends JpaRepository<OfferRelic, Long> {

  @Query(name = "getRelicListLookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAll();

  @Query(name = "getRelicListFromDeckLookup", nativeQuery = true)
  List<FullInfoDisplayObject> getAllFromDeck(@Param("deck") String deck);

}
