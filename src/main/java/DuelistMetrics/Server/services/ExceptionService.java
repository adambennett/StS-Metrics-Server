package DuelistMetrics.Server.services;


import DuelistMetrics.Server.models.LoggedException;
import DuelistMetrics.Server.models.dto.LoggedExceptionDTO;
import DuelistMetrics.Server.repositories.LoggedExceptionRepo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ExceptionService {

  private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.ExceptionService");

  private final LoggedExceptionRepo repo;

  @Autowired
  public ExceptionService(LoggedExceptionRepo repo) { this.repo = repo; }

  public LoggedException create(LoggedException exception) { return this.repo.save(exception); }

  public void logServerException(String message, Exception exception) {
    create(new LoggedException(
            "Metrics Server",
            message == null ? "" : message,
            exception != null ? exception.getMessage() : "",
            exception != null ? ExceptionUtils.getStackTrace(exception) : "",
            "server",
            "",
            "",
            new Date()
    ));
  }

  public List<LoggedExceptionDTO> searchLogsByMessage(String message) {
    return this.repo.findLogsByMessage(message, null);
  }

  public List<LoggedExceptionDTO> searchLogsByMessageDays(String message, Integer days) {
    return this.repo.findLogsByMessageDays(message, days, null);
  }

  public List<LoggedExceptionDTO> findLastXDaysOfLogs(Integer days) {
    return this.repo.findLastXDaysOfLogs(days, null);
  }

  public List<LoggedExceptionDTO> searchLogsByMessage(String message, String version) {
    return this.repo.findLogsByMessage(message, version);
  }

  public List<LoggedExceptionDTO> searchLogsByMessageDays(String message, Integer days, String version) {
    return this.repo.findLogsByMessageDays(message, days, version);
  }

  public List<LoggedExceptionDTO> findLastXDaysOfLogs(Integer days, String version) {
    return this.repo.findLastXDaysOfLogs(days, version);
  }
}
