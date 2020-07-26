package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.infoModels.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface MiniModRepo extends JpaRepository<MiniMod, Long> {

    List<MiniMod> getMiniModsByAuthorIsNull();
}
