package DuelistMetrics.Server.models;

import DuelistMetrics.Server.models.tierScore.*;

public class TierScoreCompare {

    private String act0Difference;
    private String act1Difference;
    private String act2Difference;
    private String act3Difference;
    private String overallDifference;

    public TierScoreCompare(TierScoreLookup oldCard, ScoredCard newCard, int act0Difference, int act1Difference, int act2Difference, int act3Difference, int overallDifference) {
        String score0String = act0Difference + " (" + oldCard.getAct0_score() + " -> " + newCard.getAct0_score() + ")";
        String score1String = act1Difference + " (" + oldCard.getAct1_score() + " -> " + newCard.getAct1_score() + ")";
        String score2String = act2Difference + " (" + oldCard.getAct2_score() + " -> " + newCard.getAct2_score() + ")";
        String score3String = act3Difference + " (" + oldCard.getAct3_score() + " -> " + newCard.getAct3_score() + ")";
        String scoreOverallString = overallDifference + " (" + oldCard.getOverall_score() + " -> " + newCard.getOverall_score() + ")";
        this.act0Difference = act0Difference == 0
                ? "0" : act0Difference > 0 ? "+" + score0String : "-" + score0String;
        this.act1Difference = act1Difference == 0
                ? "0" : act1Difference > 0 ? "+" + score1String : "-" + score1String;
        this.act2Difference = act2Difference == 0
                ? "0" : act2Difference > 0 ? "+" + score2String : "-" + score2String;
        this.act3Difference = act3Difference == 0
                ? "0" : act3Difference > 0 ? "+" + score3String : "-" + score3String;
        this.overallDifference = overallDifference == 0
                ? "0" : overallDifference > 0 ? "+" + scoreOverallString : "-" + scoreOverallString;
    }

    public String print(ScoredCard card) {
        return "TierScoreCompare (" + card.getCard_name() + " -- " + card.getPool_name() + ")\n" + this;
    }

    @Override
    public String toString() {
        return "{" +
                "\n    Act 0: " + act0Difference +
                ",\n    Act 1: " + act1Difference +
                ",\n    Act 2: " + act2Difference +
                ",\n    Act 3: " + act3Difference +
                ",\n    Overall: " + overallDifference +
                "\n}\n";
    }
}
