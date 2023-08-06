package DuelistMetrics.Server.models.dto;

import java.util.Date;

public record LoggedExceptionDTO(String message, String stackTrace, String uuid, Date createdDate, String duelistModVersion, String devMessage, String runUUID) {
    public LoggedExceptionDTO(String message, String stackTrace, String uuid, String duelistModVersion, String devMessage, String runUUID) {
        this(message, stackTrace, uuid, null, duelistModVersion, devMessage, runUUID);
    }
}
