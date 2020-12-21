package DuelistMetrics.Server.util;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.tierScore.*;

public class SpireUtils {

    public static int getActFromFloor(int floor) {
        return floor > 55 ? 5 : floor > 50 ? 4 : floor > 33 ? 3 : floor > 16 ? 2 : floor > 0 ? 1 : 0;
    }
    
    
}
