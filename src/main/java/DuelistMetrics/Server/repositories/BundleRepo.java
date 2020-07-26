package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface BundleRepo extends JpaRepository<Bundle, Long> {

    @Query(value = "SELECT country, COUNT(*) FROM bundle WHERE country IS NOT NULL GROUP BY country", nativeQuery = true)
    List<String> getCountryCounts();

    @Query(value = "SELECT country FROM bundle WHERE country IS NOT NULL GROUP BY country", nativeQuery = true)
    List<String> getCountries();


}
