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
                                 String runUUID) {}
