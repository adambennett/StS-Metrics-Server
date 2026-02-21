package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepo extends JpaRepository<Configuration, Long> {

    @Query(value = "SELECT * FROM configurations WHERE name = 'Scored Deck' AND active = 1", nativeQuery = true)
    List<Configuration> getActiveScoredDeckConfigurations();
}
