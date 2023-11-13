package DuelistMetrics.Server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeckNameProcessor {

  public static ArrayList<String> decks;
  public static Map<String, Integer> deckPositions;

  public static String getProperDeckName(String input) {
      return switch (input) {
          case "RS" -> "Random Deck (Small)";
          case "RB" -> "Random Deck (Big)";
          case "A1" -> "Ascended I";
          case "A2" -> "Ascended II";
          case "A3" -> "Ascended III";
          case "P1" -> "Pharaoh I";
          case "P2" -> "Pharaoh II";
          case "P3" -> "Pharaoh III";
          case "P4" -> "Pharaoh IV";
          case "P5" -> "Pharaoh V";
          default -> input + " Deck";
      };
  }

  static {
    decks = new ArrayList<>();
    deckPositions = new HashMap<>();
    decks.add("Standard Deck");
    decks.add("Dragon Deck");
    decks.add("Spellcaster Deck");
    decks.add("Aqua Deck");
    decks.add("Fiend Deck");
    decks.add("Zombie Deck");
    decks.add("Machine Deck");
    decks.add("Beast Deck");
    decks.add("Insect Deck");
    decks.add("Plant Deck");
    decks.add("Naturia Deck");
    decks.add("Warrior Deck");
    decks.add("Toon Deck");
    decks.add("Megatype Deck");
    decks.add("Increment Deck");
    decks.add("Creator Deck");
    decks.add("Exodia Deck");
    decks.add("Ascended I");
    decks.add("Ascended II");
    decks.add("Ascended III");
    decks.add("Pharaoh I");
    decks.add("Pharaoh II");
    decks.add("Pharaoh III");
    decks.add("Pharaoh IV");
    decks.add("Pharaoh V");
    decks.add("Random Deck (Small)");
    decks.add("Random Deck (Big)");
    decks.add("Upgrade Deck");
    decks.add("Metronome Deck");
    decks.add("Predaplant Deck");
    decks.add("Giant Deck");
    decks.add("Ojama Deck");

    int i = 0;
    for (String deck : decks) { deckPositions.put(deck, i++); }
  }

}
