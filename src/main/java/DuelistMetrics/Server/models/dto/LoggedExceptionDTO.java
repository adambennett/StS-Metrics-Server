package DuelistMetrics.Server.models.dto;

import java.util.Date;

public record LoggedExceptionDTO(String codebase,
                                 String message,
                                 String stackTrace,
                                 String uuid,
                                 Date createdDate,
                                 String formattedDate,
                                 String duelistModVersion,
                                 String devMessage,
                                 String runUUID,
                                 Integer occurrences,
                                 String firstOccurrence,
                                 String mostRecentOccurrence) {
    public LoggedExceptionDTO(String codebase,
                              String message,
                              String stackTrace,
                              String uuid,
                              String duelistModVersion,
                              String devMessage,
                              String runUUID,
                              Integer occurrences,
                              String firstOccurrence,
                              String mostRecentOccurrence) {
        this(codebase, message, stackTrace, uuid, null, null, duelistModVersion, devMessage, runUUID, occurrences, firstOccurrence, mostRecentOccurrence);
    }
}
