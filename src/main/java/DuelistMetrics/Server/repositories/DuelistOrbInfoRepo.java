package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.DuelistOrbInfo;
import DuelistMetrics.Server.models.dto.OrbInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuelistOrbInfoRepo extends JpaRepository<DuelistOrbInfo, Long> {

    @Query(name = "orbInfoLookup", nativeQuery = true)
    List<OrbInfoDTO> getOrbInfo(String version);

    @Query(value = "SELECT MAX(version) FROM duelist_orb_info", nativeQuery = true)
    String getLatestVersion();

}
