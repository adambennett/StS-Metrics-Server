package DuelistMetrics.Server.models;

import DuelistMetrics.Server.controllers.RunLogController.*;

import java.util.*;

public class FloorInfo {

    public String roomKey;
    public String actualRoom;
    public String encounter;
    public String hp;

    public Integer floor;
    public Integer goldChange;
    public Integer currentGold;
    public Integer currentHP;
    public Integer maxHP;
    public Integer turns;
    public Integer damage;

    public List<SimpleCardExtended> purchased;
    public List<SimpleCardExtended> obtained;
    public List<SimpleCardExtended> skipped;
    public List<SimpleCardExtended> upgraded;

    public List<Object> events;

}
