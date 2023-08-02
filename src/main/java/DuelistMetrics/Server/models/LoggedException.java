package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.LoggedExceptionDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
/*@NamedNativeQuery(name = "findLastXDaysOfLogsLookup", query = """
SELECT
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage
FROM logged_exception
WHERE DATEDIFF(created_date, CURDATE()) > ((:days) * -1)
""", resultSetMapping = "loggedExceptionDtoXDaysMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoXDaysMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "devMessage", type = String.class)})
)
@NamedNativeQuery(name = "findLogsMatchingMessageLookup", query = """
SELECT
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage
FROM logged_exception
WHERE message like CONCAT('%', :message, '%')
""", resultSetMapping = "loggedExceptionDtoMessageMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoMessageMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "devMessage", type = String.class)})
)
@NamedNativeQuery(name = "findLogsMatchingMessageByDaysLookup", query = """
SELECT
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage
FROM logged_exception
WHERE message like CONCAT('%', :message, '%') AND
      DATEDIFF(created_date, CURDATE()) > ((:days) * -1)
""", resultSetMapping = "loggedExceptionDtoMessageByDaysMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoMessageByDaysMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "devMessage", type = String.class)})
)
@NamedNativeQuery(name = "findLogsMatchingStackTraceLookup", query = """
SELECT
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage
FROM logged_exception
WHERE stack_trace like CONCAT('%', :message, '%')
""", resultSetMapping = "loggedExceptionDtoTraceMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoTraceMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "devMessage", type = String.class)})
)
@NamedNativeQuery(name = "findLogsMatchingStackTraceByDaysLookup", query = """
SELECT
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage
FROM logged_exception
WHERE stack_trace like CONCAT('%', :message, '%') AND
      DATEDIFF(created_date, CURDATE()) > ((:days) * -1)
""", resultSetMapping = "loggedExceptionDtoTraceByDaysMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoTraceByDaysMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "devMessage", type = String.class)})
)*/
public class LoggedException {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String devMessage;
  private String message;
  private String stackTrace;
  private String uniquePlayerId;
  private String duelistModVersion;
  private Date createdDate;

  public LoggedException() {}

  public LoggedException(String message, String stackTrace, String uniquePlayerId, Date createdDate, String duelistModVersion, String devMessage) {
    this.message = message;
    this.stackTrace = stackTrace;
    this.uniquePlayerId = uniquePlayerId;
    this.createdDate = createdDate;
    this.duelistModVersion = duelistModVersion;
    this.devMessage = devMessage;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public void setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
  }

  public String getUniquePlayerId() {
    return uniquePlayerId;
  }

  public void setUniquePlayerId(String uuid) {
    this.uniquePlayerId = uuid;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getDuelistModVersion() {
    return duelistModVersion;
  }

  public void setDuelistModVersion(String duelistModVersion) {
    this.duelistModVersion = duelistModVersion;
  }

  public String getDevMessage() {
    return devMessage;
  }

  public void setDevMessage(String devMessage) {
    this.devMessage = devMessage;
  }
}
