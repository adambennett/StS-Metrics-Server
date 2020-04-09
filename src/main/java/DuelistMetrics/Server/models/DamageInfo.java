package DuelistMetrics.Server.models;


public class DamageInfo {

    private Bundle bundle;

    private Integer damage;
    private Integer floor;
    private Integer turns;
    private String enemies;

    public DamageInfo() {}

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getTurns() {
        return turns;
    }

    public void setTurns(Integer turns) {
        this.turns = turns;
    }

    public String getEnemies() {
        return enemies;
    }

    public void setEnemies(String enemies) {
        this.enemies = enemies;
    }
}
