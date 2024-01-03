package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.FailedRun;
import DuelistMetrics.Server.repositories.FailedRunRepo;
import DuelistMetrics.Server.util.DatabaseLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class FailedRunService {

  private final FailedRunRepo repo;

  private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.FailedRunService");

  @Autowired
  public FailedRunService(FailedRunRepo repo) {
    this.repo = repo;
  }

  public boolean persist(String failedRunJson) {
    try {
      FailedRun newEntity = new FailedRun(failedRunJson);
      this.repo.save(newEntity);
      return true;
    } catch (Exception ex) {
      logger.info("Exception while saving FailedRun record with body= " + failedRunJson + "\n" + ExceptionUtils.getStackTrace(ex));
      DatabaseLogger.log("Exception while saving FailedRun record", ex);
      return false;
    }
  }
}
