package DuelistMetrics.Server.services;


import DuelistMetrics.Server.models.LoggedException;
import DuelistMetrics.Server.models.dto.LoggedExceptionDTO;
import DuelistMetrics.Server.repositories.LoggedExceptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ExceptionService {

  private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.ExceptionService");

  private final LoggedExceptionRepo repo;

  @Autowired
  public ExceptionService(LoggedExceptionRepo repo) { this.repo = repo; }

  public LoggedException create(LoggedException exception) { return this.repo.save(exception); }

  /*public List<LoggedExceptionDTO> searchLogsByMessage(String message) {
    return this.repo.findLogsByMessage(message);
  }

  public List<LoggedExceptionDTO> searchLogsByStackTrace(String stackTrace) {
    return this.repo.findLogsByStackTrace(stackTrace);
  }

  public List<LoggedExceptionDTO> searchLogsByMessageDays(String message, Integer days) {
    return this.repo.findLogsByMessageDays(message, days);
  }

  public List<LoggedExceptionDTO> searchLogsByStackTraceDays(String stackTrace, Integer days) {
    return this.repo.findLogsByStackTraceDays(stackTrace, days);
  }

  public List<LoggedExceptionDTO> findLastXDaysOfLogs(Integer days) {
    return this.repo.findLastXDaysOfLogs(days);
  }*/
}
