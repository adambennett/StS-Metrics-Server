package DuelistMetrics.Server.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookupRelic {

    private String description;
    private String descriptionPlain;
    private String flavor;
    private String name;
    private String id;
    private String tier;
    private String module;
    private boolean isDuelist;
    private List<String> authors;
    private List<String> pools;

}
