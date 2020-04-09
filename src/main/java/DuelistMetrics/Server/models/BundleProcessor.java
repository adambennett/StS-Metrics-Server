package DuelistMetrics.Server.models;

import DuelistMetrics.Server.controllers.*;
import DuelistMetrics.Server.models.builders.*;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.io.*;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.logging.*;

public class BundleProcessor {

  private static class Mapper<K> {
    BiFunction<K, Integer, Integer> mapper;

    public Mapper() { mapper = (k,v) -> (v==null) ? 1 : v+1; }

    public BiFunction<K,Integer,Integer> mp(int amt) {
      mapper = (k,v) -> (v==null) ? amt : v+amt;
      return mapper; }

    public BiFunction<K,Integer,Integer> mp() { return mapper; }
  }

  public static void generatePickInfos() {
    ArrayList<String> decks = new ArrayList<>();
    decks.add("Standard Deck");
    decks.add("Dragon Deck");
    decks.add("Naturia Deck");
    decks.add("Spellcaster Deck");
    decks.add("Toon Deck");
    decks.add("Zombie Deck");
    decks.add("Aqua Deck");
    decks.add("Fiend Deck");
    decks.add("Machine Deck");
    decks.add("Warrior Deck");
    decks.add("Insect Deck");
    decks.add("Plant Deck");
    decks.add("Megatype Deck");
    decks.add("Increment Deck");
    decks.add("Creator Deck");
    decks.add("Ojama Deck");
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
    for (String deck : decks) {
      for (int ascension = 0; ascension < 21; ascension++) {
        for (int challenge = -1; challenge < 21; challenge++) {
          PickInfo info = new PickInfo(deck, ascension, challenge);
          InfoController.getService().create(info);
        }
      }
    }
  }

  public static void parse(TopBundle bnd) {
    Mapper<String> com = new Mapper<>();
    Map<String, Integer> offered = new HashMap<>();
    Map<String, Integer> picked = new HashMap<>();
    Map<String, Integer> pickedVic = new HashMap<>();
    Map<String, Integer> pickedR = new HashMap<>();
    Map<String, Integer> pickedP = new HashMap<>();
    Map<String, Integer> pickedN = new HashMap<>();
    Map<String, Integer> pickedVicR = new HashMap<>();
    Map<String, Integer> pickedVicP = new HashMap<>();
    Map<String, Integer> pickedVicN = new HashMap<>();
    boolean vic = bnd.getEvent().getVictory();
    Integer asc = bnd.getEvent().getAscension_level();
    Integer chal = bnd.getEvent().getChallenge_level();
    String deck = bnd.getEvent().getStarting_deck();
    for (SpireCard c : bnd.getEvent().getCard_choices()) {
      String pick = c.getPicked();
      if (!pick.equals("SKIP")) {
        StringBuilder bd = new StringBuilder();
        bd.append(pick);
        if (bd.indexOf("+") > 0) {
          pick = pick.substring(0, bd.indexOf("+"));
        }
        offered.compute(pick, com.mp());
        picked.compute(pick, com.mp());
      }
      List<String> notPicked = c.getNot_picked();
      for (String s : notPicked) {
        StringBuilder b = new StringBuilder();
        b.append(s);
        if (b.indexOf("+") > 0) {
          s = s.substring(0, b.indexOf("+"));
        }
        offered.compute(s, com.mp());
      }
      if (vic && !pick.equals("SKIP")) { pickedVic.compute(pick, com.mp()); }
    }

    for (String r : bnd.getEvent().getRelics()) {
      pickedR.compute(r, com.mp());
      if (vic) { pickedVicR.compute(r, com.mp()); }
    }

    for (Potion p : bnd.getEvent().getPotions_obtained()) {
      String pick = p.getKey();
      pickedP.compute(pick, com.mp());
      if (vic) { pickedVicP.compute(pick, com.mp()); }
    }

    String neow = bnd.getEvent().getNeow_bonus();
    pickedN.compute(neow, (k,v) -> (v==null) ? 1 : v+1);
    if (vic) { pickedVicN.compute(neow, (k,v) -> (v==null) ? 1 : v+1); }



    // Pinfo
    if (chal == null) { chal = -1; }
    PickInfo info = InfoController.getService().findInfo(deck, asc, chal);
    if (info == null) {
      info = new PickInfo(deck, asc, chal);
    }

    // Cards
    for (Map.Entry<String, Integer> entry : offered.entrySet()) { info.addCard(new OfferCard(entry.getKey(), entry.getValue(), 0, 0, info)); }
    for (Map.Entry<String, Integer> entry : picked.entrySet()) {
      boolean found = false;
      for (OfferCard c : info.getCards()) {
        if (c.getName().equals(entry.getKey())) {
          c.setPicked(entry.getValue());
          found = true;
          break;
        }
      }
      if (!found) { info.addCard(new OfferCard(entry.getKey(), 0, entry.getValue(), 0, info)); }
    }
    for (Map.Entry<String, Integer> entry : pickedVic.entrySet()) {
      boolean found = false;
      for (OfferCard c : info.getCards()) {
        if (c.getName().equals(entry.getKey())) {
          c.setPickVic(entry.getValue());
          found = true;
          break;
        }
      }
      if (!found) { info.addCard(new OfferCard(entry.getKey(), 0, entry.getValue(), entry.getValue(), info)); }
    }

    // Relics
    for (Map.Entry<String, Integer> entry : pickedR.entrySet()) { info.addRelic(new OfferRelic(entry.getKey(), entry.getValue(), 0, info)); }
    for (Map.Entry<String, Integer> entry : pickedVicR.entrySet()) {
      boolean found = false;
      for (OfferRelic r : info.getRelics()) {
        if (r.getName().equals(entry.getKey())) {
          r.setPickVic(entry.getValue());
          found = true;
          break;
        }
      }
      if (!found) { info.addRelic(new OfferRelic(entry.getKey(), entry.getValue(), entry.getValue(), info)); }
    }

    // Potions
    for (Map.Entry<String, Integer> entry : pickedP.entrySet()) { info.addPotion(new OfferPotion(entry.getKey(), entry.getValue(), 0, info)); }

    for (Map.Entry<String, Integer> entry : pickedVicP.entrySet()) {
      boolean found = false;
      for (OfferPotion p : info.getPotions()) {
        if (p.getName().equals(entry.getKey())) {
          p.setPickVic(entry.getValue());
          found = true;
          break;
        }
      }
      if (!found) { info.addPotion(new OfferPotion(entry.getKey(), entry.getValue(), entry.getValue(), info)); }
    }

    // Neow
    for (Map.Entry<String, Integer> entry : pickedN.entrySet()) { info.addNeow(new OfferNeow(entry.getKey(), entry.getValue(), 0, info)); }
    for (Map.Entry<String, Integer> entry : pickedVicN.entrySet()) {
      boolean found = false;
      for (OfferNeow n : info.getNeow()) {
        if (n.getName().equals(entry.getKey())) {
          n.setPickVic(entry.getValue());
          found = true;
          break;
        }
      }
      if (!found) { info.addNeow(new OfferNeow(entry.getKey(), entry.getValue(), entry.getValue(), info)); }
    }

    InfoController.getService().create(info);
    Boolean kaiba = bnd.getEvent().getPlaying_as_kaiba();
    String killedBy = bnd.getEvent().getKilled_by();
    if (kaiba == null) { kaiba = false; }
    if (killedBy == null || killedBy.equals(" ")) { killedBy = "Self"; }
    RunLog log = new RunLogBuilder()
      .setAscension(asc)
      .setChallenge(chal)
      .setDeck(deck)
      .setFloor(bnd.getEvent().getFloor_reached())
      .setHost(bnd.getHost())
      .setKaiba(kaiba)
      .setKilledBy(killedBy)
      .setVictory(bnd.getEvent().getVictory())
      .createRunLog();
    RunLogController.getService().create(log);
  }

  public static void parse() throws IOException {
    ArrayList<TopBundle> bundles = readIn();
    Mapper<String> com = new Mapper<>();
    for (TopBundle bnd : bundles) {
      Map<String, Integer> offered = new HashMap<>();
      Map<String, Integer> picked = new HashMap<>();
      Map<String, Integer> pickedVic = new HashMap<>();
      Map<String, Integer> pickedR = new HashMap<>();
      Map<String, Integer> pickedP = new HashMap<>();
      Map<String, Integer> pickedN = new HashMap<>();
      Map<String, Integer> pickedVicR = new HashMap<>();
      Map<String, Integer> pickedVicP = new HashMap<>();
      Map<String, Integer> pickedVicN = new HashMap<>();
      boolean vic = bnd.getEvent().getVictory();
      Integer asc = bnd.getEvent().getAscension_level();
      Integer chal = bnd.getEvent().getChallenge_level();
      String deck = bnd.getEvent().getStarting_deck();
      for (SpireCard c : bnd.getEvent().getCard_choices()) {
        String pick = c.getPicked();
        if (!pick.equals("SKIP")) {
          StringBuilder bd = new StringBuilder();
          bd.append(pick);
          if (bd.indexOf("+") > 0) {
            pick = pick.substring(0, bd.indexOf("+"));
          }
          offered.compute(pick, com.mp());
          picked.compute(pick, com.mp());
        }
        List<String> notPicked = c.getNot_picked();
        for (String s : notPicked) {
          StringBuilder b = new StringBuilder();
          b.append(s);
          if (b.indexOf("+") > 0) {
            s = s.substring(0, b.indexOf("+"));
          }
          offered.compute(s, com.mp());
        }
        if (vic && !pick.equals("SKIP")) { pickedVic.compute(pick, com.mp()); }
      }

      for (String r : bnd.getEvent().getRelics()) {
        pickedR.compute(r, com.mp());
        if (vic) { pickedVicR.compute(r, com.mp()); }
      }

      for (Potion p : bnd.getEvent().getPotions_obtained()) {
        String pick = p.getKey();
        pickedP.compute(pick, com.mp());
        if (vic) { pickedVicP.compute(pick, com.mp()); }
      }

      String neow = bnd.getEvent().getNeow_bonus();
      pickedN.compute(neow, (k,v) -> (v==null) ? 1 : v+1);
      if (vic) { pickedVicN.compute(neow, (k,v) -> (v==null) ? 1 : v+1); }



      // Pinfo
      if (chal == null) { chal = -1; }
      PickInfo info = InfoController.getService().findInfo(deck, asc, chal);
      if (info == null) {
        info = new PickInfo(deck, asc, chal);
      }

      // Cards
      for (Map.Entry<String, Integer> entry : offered.entrySet()) { info.addCard(new OfferCard(entry.getKey(), entry.getValue(), 0, 0, info)); }
      for (Map.Entry<String, Integer> entry : picked.entrySet()) {
        boolean found = false;
        for (OfferCard c : info.getCards()) {
          if (c.getName().equals(entry.getKey())) {
            c.setPicked(entry.getValue());
            found = true;
            break;
          }
        }
        if (!found) { info.addCard(new OfferCard(entry.getKey(), 0, entry.getValue(), 0, info)); }
      }
      for (Map.Entry<String, Integer> entry : pickedVic.entrySet()) {
        boolean found = false;
        for (OfferCard c : info.getCards()) {
          if (c.getName().equals(entry.getKey())) {
            c.setPickVic(entry.getValue());
            found = true;
            break;
          }
        }
        if (!found) { info.addCard(new OfferCard(entry.getKey(), 0, entry.getValue(), entry.getValue(), info)); }
      }

      // Relics
      for (Map.Entry<String, Integer> entry : pickedR.entrySet()) { info.addRelic(new OfferRelic(entry.getKey(), entry.getValue(), 0, info)); }
      for (Map.Entry<String, Integer> entry : pickedVicR.entrySet()) {
        boolean found = false;
        for (OfferRelic r : info.getRelics()) {
          if (r.getName().equals(entry.getKey())) {
            r.setPickVic(entry.getValue());
            found = true;
            break;
          }
        }
        if (!found) { info.addRelic(new OfferRelic(entry.getKey(), entry.getValue(), entry.getValue(), info)); }
      }

      // Potions
      for (Map.Entry<String, Integer> entry : pickedP.entrySet()) { info.addPotion(new OfferPotion(entry.getKey(), entry.getValue(), 0, info)); }

      for (Map.Entry<String, Integer> entry : pickedVicP.entrySet()) {
        boolean found = false;
        for (OfferPotion p : info.getPotions()) {
          if (p.getName().equals(entry.getKey())) {
            p.setPickVic(entry.getValue());
            found = true;
            break;
          }
        }
        if (!found) { info.addPotion(new OfferPotion(entry.getKey(), entry.getValue(), entry.getValue(), info)); }
      }

      // Neow
      for (Map.Entry<String, Integer> entry : pickedN.entrySet()) { info.addNeow(new OfferNeow(entry.getKey(), entry.getValue(), 0, info)); }
      for (Map.Entry<String, Integer> entry : pickedVicN.entrySet()) {
        boolean found = false;
        for (OfferNeow n : info.getNeow()) {
          if (n.getName().equals(entry.getKey())) {
            n.setPickVic(entry.getValue());
            found = true;
            break;
          }
        }
        if (!found) { info.addNeow(new OfferNeow(entry.getKey(), entry.getValue(), entry.getValue(), info)); }
      }

      InfoController.getService().create(info);
      Boolean kaiba = bnd.getEvent().getPlaying_as_kaiba();
      String killedBy = bnd.getEvent().getKilled_by();
      if (kaiba == null) { kaiba = false; }
      if (killedBy == null || killedBy.equals(" ")) { killedBy = "Self"; }
      RunLog log = new RunLogBuilder()
        .setAscension(asc)
        .setChallenge(chal)
        .setDeck(deck)
        .setFloor(bnd.getEvent().getFloor_reached())
        .setHost(bnd.getHost())
        .setKaiba(kaiba)
        .setKilledBy(killedBy)
        .setVictory(bnd.getEvent().getVictory())
        .createRunLog();
      RunLogController.getService().create(log);
    }
  }

  public static ArrayList<TopBundle> readIn() throws IOException {

    File folder = new File("C:/Users/eX_Di/git/StS-Metrics-Server/src/main/resources/runs");
    File[] listOfFiles = folder.listFiles();
    if (listOfFiles != null) {
      ArrayList<TopBundle> output = new ArrayList<>();
      if (listOfFiles.length > 0) {
        for (File file : listOfFiles) {
          if (file.isFile()) {
            output.add(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(FileUtils.readFileToString(file), TopBundle.class));
          }
        }
      }
      return output;
    }
    Logger.getGlobal().warning("Did not read in runs properly!");
    return new ArrayList<>();
  }
}
