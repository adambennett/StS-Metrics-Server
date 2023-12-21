package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.ConfigDifference;
import DuelistMetrics.Server.models.dto.ConfigDifferenceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigDifferenceRepo extends JpaRepository<ConfigDifference, Long> {

  @Query(name = "getListOfConfigDifferencesByRunLookup", nativeQuery = true)
  List<ConfigDifferenceDTO> getListOfConfigDifferencesByRun(Long bundleId);

  @Query(value = "SELECT id FROM config_difference WHERE data_hash = md5(concat(:category,:type,:setting,:defaultValue,:playerValue))", nativeQuery = true)
  Long findIdByDataHashValues(String category, String type, String setting, String defaultValue, String playerValue);

}
