package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.dto.KeywordDTO;
import DuelistMetrics.Server.models.infoModels.InfoKeyword;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface InfoKeywordRepo extends JpaRepository<InfoKeyword, Long> {

    @Query(name = "getDuelistKeywordListLookup", nativeQuery = true)
    List<KeywordDTO> getDuelistKeywordListLookup();

}
