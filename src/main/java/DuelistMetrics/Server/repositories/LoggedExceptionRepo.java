package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.LoggedException;
import DuelistMetrics.Server.models.dto.LoggedExceptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggedExceptionRepo extends JpaRepository<LoggedException, Long> {

  @Query(name = "findLogsMatchingMessageLookup", nativeQuery = true)
  List<LoggedExceptionDTO> findLogsByMessage(String message, String version);

  @Query(name = "findLogsMatchingMessageByDaysLookup", nativeQuery = true)
  List<LoggedExceptionDTO> findLogsByMessageDays(String message, Integer days, String version);

  @Query(name = "findLastXDaysOfLogsLookup", nativeQuery = true)
  List<LoggedExceptionDTO> findLastXDaysOfLogs(Integer days, String version);

}
