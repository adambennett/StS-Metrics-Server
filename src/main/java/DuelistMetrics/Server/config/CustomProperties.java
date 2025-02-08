package DuelistMetrics.Server.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;

@NoArgsConstructor
@Getter
@Setter
@Component
@ConfigurationProperties("metrics.property")
public class CustomProperties {

    public Boolean enableAutomaticUpdates = true;
    public Boolean showUpdateProgress = false;
    public Boolean showCardsUpdated = false;
    public Boolean allowShutdownEndpoint = false;

}
