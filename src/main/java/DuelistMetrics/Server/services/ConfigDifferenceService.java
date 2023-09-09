package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.BundleConfigDifferenceXREF;
import DuelistMetrics.Server.models.ConfigDifference;
import DuelistMetrics.Server.models.dto.ConfigDifferenceDTO;
import DuelistMetrics.Server.repositories.BundleConfigDifferenceXREFRepo;
import DuelistMetrics.Server.repositories.ConfigDifferenceRepo;
import DuelistMetrics.Server.util.DatabaseLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ConfigDifferenceService {

  private final ConfigDifferenceRepo repo;
  private final BundleConfigDifferenceXREFRepo xrefRepo;

  private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.ConfigDifferenceService");

  @Autowired
  public ConfigDifferenceService(ConfigDifferenceRepo repo, BundleConfigDifferenceXREFRepo xrefRepo) {
    this.repo = repo;
    this.xrefRepo = xrefRepo;
  }

  public List<ConfigDifferenceDTO> getDifferencesByRunId(Long topBundleId) {
    var result = this.repo.getListOfConfigDifferencesByRun(topBundleId);
    return result == null ? new ArrayList<>() : result;
  }

  public Long create(ConfigDifferenceDTO difference) {
    if (difference == null) return null;

    Long checkId = this.repo.findIdByDataHashValues(difference.category(), difference.type(), difference.setting(), difference.defaultValue(), difference.playerValue());
    if (checkId != null) {
      return checkId;
    }

    ConfigDifference newEntity = new ConfigDifference();
    newEntity.setCategory(difference.category());
    newEntity.setType(difference.type());
    newEntity.setSetting(difference.setting());
    newEntity.setDefault_value(difference.defaultValue());
    newEntity.setPlayer_value(difference.playerValue());

    try {
      return this.repo.save(newEntity).getId();
    } catch (Exception ex) {
      logger.info("Exception while saving/fetching ConfigDifference record" + "\n" + ExceptionUtils.getStackTrace(ex));
      DatabaseLogger.log("Exception while saving/fetching ConfigDifference record", ex);
      return null;
    }
  }

  public boolean createXrefs(List<Long> differenceIds, Long runId) {
    List<BundleConfigDifferenceXREF> newEntities = new ArrayList<>();
    for (Long diffId : differenceIds) {
      BundleConfigDifferenceXREF newEntity = new BundleConfigDifferenceXREF();
      newEntity.setBundle_id(runId);
      newEntity.setDifference_id(diffId);
      newEntities.add(newEntity);
    }
    try {
      this.xrefRepo.saveAll(newEntities);
      return true;
    } catch (Exception ex) {
      logger.info("Exception while saving BundleConfigDifferenceXREF record with runId=" + runId + ", dofferenceIds=" + differenceIds + "\n" + ExceptionUtils.getStackTrace(ex));
      DatabaseLogger.log("Exception while saving BundleConfigDifferenceXREF record with runId=" + runId + ", differenceIds=" + differenceIds, ex);
      return false;
    }
  }

}
