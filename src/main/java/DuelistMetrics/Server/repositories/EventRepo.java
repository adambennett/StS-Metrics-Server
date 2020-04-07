package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {}
