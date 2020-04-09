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

}
