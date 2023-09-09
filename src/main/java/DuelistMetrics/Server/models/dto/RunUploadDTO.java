package DuelistMetrics.Server.models.dto;

import DuelistMetrics.Server.models.TopBundle;

import java.util.List;

public record RunUploadDTO(TopBundle runBundle,
                           List<ConfigDifferenceDTO> configDifferences) {}
