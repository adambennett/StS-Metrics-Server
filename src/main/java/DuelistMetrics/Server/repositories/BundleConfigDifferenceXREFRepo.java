package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.BundleConfigDifferenceXREF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleConfigDifferenceXREFRepo extends JpaRepository<BundleConfigDifferenceXREF, Long> {}
