package DuelistMetrics.Server.util;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.runDetails.*;
import DuelistMetrics.Server.models.tierScore.*;

import java.util.*;

public class SpireUtils {

    public static int getActFromFloor(int floor) {
        return floor > 55 ? 5 : floor > 50 ? 4 : floor > 33 ? 3 : floor > 16 ? 2 : floor > 0 ? 1 : 0;
    }

    public static String parseBaseId(String cardId, Map<String, String> cardMapping, Map<String, String> relicMapping, Map<String, String> potionMapping) {
        if (cardId == null) {
            return null;
        }
        if (cardId.contains("+")) {
            String[] splice = cardId.split("\\+");
            String upgradeAmount = splice[1];
            String baseId = splice[0];
            if (cardMapping.containsKey(baseId)) {
                String name = cardMapping.get(baseId);
                return name + "+" + upgradeAmount;
            }
        }
        if (cardMapping.containsKey(cardId)) {
            return cardMapping.get(cardId);
        } else if (relicMapping.containsKey(cardId)) {
            return relicMapping.get(cardId);
        } else if (potionMapping.containsKey(cardId)) {
            return potionMapping.get(cardId);
        }
        return cardId;
    }

    public static SimpleCard parseBaseIdToSimpleCard(String cardId, Map<String, String> cardMapping, Map<String, String> relicMapping, Map<String, String> potionMapping) {
        String name = parseBaseId(cardId, cardMapping, relicMapping, potionMapping);
        SimpleCard card = new SimpleCard(cardId);
        card.name = name;
        return card;
    }
    
}
