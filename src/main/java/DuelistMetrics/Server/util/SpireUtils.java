package DuelistMetrics.Server.util;

import DuelistMetrics.Server.controllers.RunLogController.*;
import DuelistMetrics.Server.models.runDetails.*;

import java.util.*;

public class SpireUtils {

    public static int getActFromFloor(int floor) {
        return floor > 55 ? 5 : floor > 50 ? 4 : floor > 33 ? 3 : floor > 16 ? 2 : floor > 0 ? 1 : 0;
    }

    public record SpireParseInfo(String name, SimpleCardExtendedType type){}
    public record SpireParseInfoFull(String name, SimpleCardExtendedType type, SimpleCard card){}

    public static SpireParseInfo parseBaseId(String cardId, Map<String, String> cardMapping, Map<String, String> relicMapping, Map<String, String> potionMapping) {
        if (cardId == null) {
            return null;
        }
        if (cardId.contains("+")) {
            String[] splice = cardId.split("\\+");
            String upgradeAmount = splice[1];
            String baseId = splice[0];
            if (cardMapping.containsKey(baseId)) {
                String name = cardMapping.get(baseId);
                return new SpireParseInfo(name + "+" + upgradeAmount, SimpleCardExtendedType.Card);
            }
        }
        if (cardMapping.containsKey(cardId)) {
            return new SpireParseInfo(cardMapping.get(cardId), SimpleCardExtendedType.Card);
        } else if (relicMapping.containsKey(cardId)) {
            return new SpireParseInfo(relicMapping.get(cardId), SimpleCardExtendedType.Relic);
        } else if (potionMapping.containsKey(cardId)) {
            return new SpireParseInfo(potionMapping.get(cardId), SimpleCardExtendedType.Potion);
        }
        return new SpireParseInfo(cardId, SimpleCardExtendedType.Unknown);
    }

    public static SpireParseInfoFull parseBaseIdToSimpleCard(String cardId, Map<String, String> cardMapping, Map<String, String> relicMapping, Map<String, String> potionMapping) {
        var info = parseBaseId(cardId, cardMapping, relicMapping, potionMapping);
        SimpleCard card = new SimpleCard(cardId);
        card.name = info.name;
        return new SpireParseInfoFull(info.name, info.type, card);
    }
    
}
