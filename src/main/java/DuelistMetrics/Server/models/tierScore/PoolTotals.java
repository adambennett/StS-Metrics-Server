package DuelistMetrics.Server.models.tierScore;

public class PoolTotals {

    public String pool;
    public String deck;
    public int cards;
    public float act0;
    public float act1;
    public float act2;
    public float act3;

    public PoolTotals(String pool) {
        this.pool = pool;
        this.act0 = 0.0f;
        this.act1 = 0.0f;
        this.act2 = 0.0f;
        this.act3 = 0.0f;
    }
}
