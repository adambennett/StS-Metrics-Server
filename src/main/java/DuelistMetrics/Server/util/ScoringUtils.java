package DuelistMetrics.Server.util;

import DuelistMetrics.Server.models.tierScore.*;

import java.util.*;

public class ScoringUtils {

    public static void sortAndCalculatePercentiles(Map<String, Map<String, PopsCard>> cardPopularityMap, Map<String, List<PopsCard>> sortedPopularityMap, Map<String, String> poolToDeckConverter) {
        cardPopularityMap.forEach((pool, popsCards) -> {
            String deck = poolToDeckConverter.get(pool);
            for (Map.Entry<String, PopsCard> innerEntry : popsCards.entrySet()) {
                sortedPopularityMap.get(deck).add(innerEntry.getValue());
            }
            Collections.sort(sortedPopularityMap.get(deck));
            List<PopsCard> cards = sortedPopularityMap.get(deck);
            int index = (int) (0.4 * cards.size());
            for (int i = 0; i < cards.size(); i++) {
                PopsCard card = cards.get(i);
                int position = i + 1;
                boolean above40 = (position) >= index;
                card.position = position;
                if (above40) {
                    card.aboveFortyPercentile = true;
                }
                card.percentile = LocalProccesor.calculatePercentile(position, cards.size(), above40);
            }
        });
    }

}
