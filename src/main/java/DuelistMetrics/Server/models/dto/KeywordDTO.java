package DuelistMetrics.Server.models.dto;

import java.util.ArrayList;
import java.util.Arrays;

public record KeywordDTO(String keyword, String description, String allNames) {
    public FormattedKeywordDTO format(String delimiter) {
        try {
            return new FormattedKeywordDTO(this.keyword(), this.description(), new ArrayList<>(Arrays.asList(this.allNames().split(delimiter))));
        } catch (Exception ex) {
            return new FormattedKeywordDTO(this.keyword(), this.description(), new ArrayList<>());
        }
    }
}
