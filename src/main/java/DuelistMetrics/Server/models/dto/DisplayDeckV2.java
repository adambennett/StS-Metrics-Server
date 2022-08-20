package DuelistMetrics.Server.models.dto;

import DuelistMetrics.Server.util.*;

import java.util.*;

public record DisplayDeckV2(String deck, String mostKilledBy, Integer runs, Integer wins, Integer floor,
                            Integer kaiba, Integer highestChallenge, List<Integer> highestChallengeRunID,
                            List<Integer> highestFloorRunID, List<Integer> poolCardIds,
                            List<Integer> basicCardIds, List<Integer> startingDeckCardIds, List<Integer> relicIds, List<Integer> potionIds) implements Comparable<DisplayDeckV2> {
    @Override
    public int compareTo(DisplayDeckV2 o) {
        return (DeckNameProcessor.deckPositions.containsKey(this.deck) && DeckNameProcessor.deckPositions.containsKey(o.deck)) ? DeckNameProcessor.deckPositions.get(this.deck).compareTo(DeckNameProcessor.deckPositions.get(o.deck())) : 0;
    }
}
