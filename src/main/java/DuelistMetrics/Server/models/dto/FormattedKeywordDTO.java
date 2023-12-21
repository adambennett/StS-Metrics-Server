package DuelistMetrics.Server.models.dto;

import java.util.List;

public record FormattedKeywordDTO(String keyword,
                                  String description,
                                  List<String> allNames) {}
