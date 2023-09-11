package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.FailedRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedRunRepo extends JpaRepository<FailedRun, Long> {}
