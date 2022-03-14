package DuelistMetrics.Server.models;

public class TierScoreLookup {

    private final String card_name;
    private final Integer overall_score;
    private final Integer act0_score;
    private final Integer act1_score;
    private final Integer act2_score;
    private final Integer act3_score;

    public TierScoreLookup(String card_name, Integer overall_score, Integer act0_score, Integer act1_score, Integer act2_score, Integer act3_score) {
        this.card_name = card_name;
        this.overall_score = overall_score;
        this.act0_score = act0_score;
        this.act1_score = act1_score;
        this.act2_score = act2_score;
        this.act3_score = act3_score;
    }

    public String getCard_name() {
        return card_name;
    }
    public Integer getOverall_score() {
        return overall_score;
    }
    public Integer getAct0_score() {
        return act0_score;
    }
    public Integer getAct1_score() {
        return act1_score;
    }
    public Integer getAct2_score() {
        return act2_score;
    }
    public Integer getAct3_score() {
        return act3_score;
    }
}
