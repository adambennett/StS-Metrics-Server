package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TopBundleRepo extends JpaRepository<TopBundle, Long> {

    @Query(value = "SELECT * FROM top_bundle WHERE top_id > ((SELECT MAX(top_id) FROM top_bundle) - :amt)", nativeQuery = true)
    List<TopBundle> getMostRecentRuns(int amt);

}
