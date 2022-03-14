package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.tierScore.*;

public class TierScoreCompare {

    private int act0Difference;
    private int act1Difference;
    private int act2Difference;
    private int act3Difference;
    private int overallDifference;

    public TierScoreCompare(int act0Difference, int act1Difference, int act2Difference, int act3Difference, int overallDifference) {
        this.act0Difference = act0Difference;
        this.act1Difference = act1Difference;
        this.act2Difference = act2Difference;
        this.act3Difference = act3Difference;
        this.overallDifference = overallDifference;
    }

    public String print(ScoredCard card) {
        return "TierScoreCompare (" + card.getCard_name() + " -- " + card.getPool_name() + ")\n" + this;
    }

    @Override
    public String toString() {
        return "{\n" +
                "act0Difference: " + act0Difference +
                ",\n act1Difference: " + act1Difference +
                ",\n act2Difference: " + act2Difference +
                ",\n act3Difference: " + act3Difference +
                ",\n overallDifference: " + overallDifference +
                "\n}\n";
    }
}
