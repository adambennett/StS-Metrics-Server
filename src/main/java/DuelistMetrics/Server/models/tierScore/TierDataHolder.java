package DuelistMetrics.Server.models.tierScore;

public class TierDataHolder {
    public int wins;
    public int losses;
    public int act;
    public String cardId;

    public TierDataHolder(String cardId, int act) {
        this.wins = 0;
        this.losses = 0;
        this.act = act;
        this.cardId = cardId;
    }
}
