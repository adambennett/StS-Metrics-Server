package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.LoggedExceptionDTO;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

import java.util.Date;

@Entity
@NamedNativeQuery(name = "findLastXDaysOfLogsLookup", query = """
SELECT
  codebase,
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage,
  run_uuid AS runUUID
FROM logged_exception
WHERE DATEDIFF(created_date, CURDATE()) > ((:days) * -1) AND
      (:version IS NULL OR duelist_mod_version = :version)
""", resultSetMapping = "loggedExceptionDtoXDaysMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoXDaysMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "codebase", type = String.class),
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "devMessage", type = String.class),
                @ColumnResult(name = "runUUID", type = String.class)})
)
@NamedNativeQuery(name = "findLogsMatchingMessageLookup", query = """
SELECT
  codebase,
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage,
  run_uuid AS runUUID
FROM logged_exception
WHERE (message LIKE CONCAT('%', :message, '%') OR (stack_trace LIKE CONCAT('%', :message '%'))) AND
      (:version IS NULL OR duelist_mod_version = :version)
""", resultSetMapping = "loggedExceptionDtoMessageMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoMessageMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "codebase", type = String.class),
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "runUUID", type = String.class)})
)
@NamedNativeQuery(name = "findLogsMatchingMessageByDaysLookup", query = """
SELECT
  codebase,
  message,
  stack_trace AS stackTrace,
  unique_player_id AS uuid,
  created_date AS createdDate,
  duelist_mod_version AS duelistModVersion,
  dev_message AS devMessage,
  run_uuid AS runUUID
FROM logged_exception
WHERE (message LIKE CONCAT('%', :message, '%') OR (stack_trace LIKE CONCAT('%', :message '%'))) AND
      (DATEDIFF(created_date, CURDATE()) > ((:days) * -1)) AND
      (:version IS NULL OR duelist_mod_version = :version)
""", resultSetMapping = "loggedExceptionDtoMessageByDaysMapping")
@SqlResultSetMapping(
        name = "loggedExceptionDtoMessageByDaysMapping",
        classes = @ConstructorResult(targetClass = LoggedExceptionDTO.class,columns = {
                @ColumnResult(name = "codebase", type = String.class),
                @ColumnResult(name = "message", type = String.class),
                @ColumnResult(name = "stackTrace", type = String.class),
                @ColumnResult(name = "uuid", type = String.class),
                @ColumnResult(name = "createdDate", type = Date.class),
                @ColumnResult(name = "duelistModVersion", type = String.class),
                @ColumnResult(name = "runUUID", type = String.class)})
)
public class LoggedException {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String codebase;
  private String dev_message;
  private String message;
  private String stack_trace;
  private String unique_player_id;
  private String duelist_mod_version;
  private String run_uuid;
  private Date created_date;

  public LoggedException() {}

  public LoggedException(String message, String stack_trace, String unique_player_id, Date created_date, String duelist_mod_version, String dev_message, String run_uuid) {
    this("DuelistMod", dev_message, message, stack_trace, unique_player_id, duelist_mod_version, run_uuid, created_date);
  }

  public LoggedException(String codebase, String dev_message, String message, String stack_trace, String unique_player_id, String duelist_mod_version, String run_uuid, Date created_date) {
    this.codebase = codebase;
    this.dev_message = dev_message;
    this.message = message;
    this.stack_trace = stack_trace;
    this.unique_player_id = unique_player_id;
    this.duelist_mod_version = duelist_mod_version;
    this.run_uuid = run_uuid;
    this.created_date = created_date;
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

  public String getStack_trace() {
    return stack_trace;
  }

  public void setStack_trace(String stackTrace) {
    this.stack_trace = stackTrace;
  }

  public String getUnique_player_id() {
    return unique_player_id;
  }

  public void setUnique_player_id(String uuid) {
    this.unique_player_id = uuid;
  }

  public Date getCreated_date() {
    return created_date;
  }

  public void setCreated_date(Date createdDate) {
    this.created_date = createdDate;
  }

  public String getDuelist_mod_version() {
    return duelist_mod_version;
  }

  public void setDuelist_mod_version(String duelistModVersion) {
    this.duelist_mod_version = duelistModVersion;
  }

  public String getDev_message() {
    return dev_message;
  }

  public void setDev_message(String devMessage) {
    this.dev_message = devMessage;
  }

  public String getRun_uuid() {
    return run_uuid;
  }

  public void setRun_uuid(String runUUID) {
    this.run_uuid = runUUID;
  }

  public String getCodebase() {
    return codebase;
  }

  public void setCodebase(String codebase) {
    this.codebase = codebase;
  }
}
