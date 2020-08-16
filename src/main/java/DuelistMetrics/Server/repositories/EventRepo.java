package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

  @Query(value = "SELECT player_choice FROM event WHERE event_name = 'Visit From Anubis' AND player_choice LIKE 'Scored:%'", nativeQuery = true)
  List<String> getScoringChoices();

  @Query(value = "SELECT COUNT(*) FROM event WHERE event_name = 'Visit From Anubis'", nativeQuery = true)
  String getNumberOfVisits();

}
