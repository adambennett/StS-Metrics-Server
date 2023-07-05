package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.dto.RunLogDTO;

public record RunLogWithUTC(RunLogDTO log, String utcTimestamp) {}
