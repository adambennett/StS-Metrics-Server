package DuelistMetrics.Server.config;

import org.springframework.boot.context.properties.*;
import org.springframework.stereotype.*;

@Component
@ConfigurationProperties("metrics.property")
public class CustomProperties {

    public Boolean enableAutomaticUpdates = true;
    public Boolean showUpdateProgress = false;
    public Boolean showCardsUpdated = false;

    public CustomProperties() {}

    public Boolean getEnableAutomaticUpdates() {
        return enableAutomaticUpdates;
    }

    public void setEnableAutomaticUpdates(Boolean enableAutomaticUpdates) {
        this.enableAutomaticUpdates = enableAutomaticUpdates;
    }

    public Boolean getShowUpdateProgress() {
        return showUpdateProgress;
    }

    public void setShowUpdateProgress(Boolean showUpdateProgress) {
        this.showUpdateProgress = showUpdateProgress;
    }

    public Boolean getShowCardsUpdated() {
        return showCardsUpdated;
    }

    public void setShowCardsUpdated(Boolean showCardsUpdated) {
        this.showCardsUpdated = showCardsUpdated;
    }
}
