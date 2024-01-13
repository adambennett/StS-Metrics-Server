package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.PickInfoV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepo extends JpaRepository<PickInfoV2, Long> {

    @Query(value = "SELECT id FROM pick_info_v2 WHERE data_hash = md5(concat(:deck,'CHALLENGE',:challenge,'ASCENSION',:asc))", nativeQuery = true)
    Long findIdByDataHashValues(String deck, int asc, int challenge);

}
