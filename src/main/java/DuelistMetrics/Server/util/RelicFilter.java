package DuelistMetrics.Server.util;

import DuelistMetrics.Server.models.*;

import java.util.*;

public class RelicFilter {
    
    private final Map<String, String> disallowedRelics;
    private static RelicFilter instance;
    
    private RelicFilter() { 
        disallowedRelics = new HashMap<>(); 
        fillList();
    }
    
    private void fillList() {
        this.disallowedRelics.put("theDuelist:MillenniumPuzzle", "");
        this.disallowedRelics.put("theDuelist:ChallengePuzzle", "");
        this.disallowedRelics.put("theDuelist:CardPoolRelic", "");
        this.disallowedRelics.put("theDuelist:CardPoolBasicRelic", "");
        this.disallowedRelics.put("theDuelist:BoosterPackPoolRelic", "");
        this.disallowedRelics.put("theDuelist:CardPoolAddRelic", "");
        this.disallowedRelics.put("theDuelist:CardPoolMinusRelic", "");
        this.disallowedRelics.put("theDuelist:CardPoolSaveRelic", "");
        this.disallowedRelics.put("theDuelist:CardPoolOptionsRelic", "");
    }

    public Boolean allowed(String r) {
        return !this.disallowedRelics.containsKey(r);
    }
    
    public static RelicFilter getInstance() { return instance; }
    
    static { 
       try { instance = new RelicFilter(); }
       catch (Exception ex) { throw new RuntimeException("Failed to initialize RelicFilter!"); }
    }
    
}
