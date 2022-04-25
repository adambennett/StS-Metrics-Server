package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Repository
public interface TopBundleRepo extends JpaRepository<TopBundle, Long> {

    @Query(value = "SELECT * FROM top_bundle WHERE top_id > ((SELECT MAX(top_id) FROM top_bundle) - :amt)", nativeQuery = true)
    List<TopBundle> getMostRecentRuns(int amt);

    @Query(value = "SELECT * FROM top_bundle t JOIN bundle b on t.event_top_id = b.top_id WHERE t.host = :host and b.local_time = :localTime", nativeQuery = true)
    List<TopBundle> findByHostAndLocalTime(String host, BigDecimal localTime);

}
