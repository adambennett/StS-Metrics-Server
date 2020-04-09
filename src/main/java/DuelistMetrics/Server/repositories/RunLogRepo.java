package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface RunLogRepo extends JpaRepository<RunLog, Long> {}
