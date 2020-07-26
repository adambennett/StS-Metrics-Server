package DuelistMetrics.Server.models;

import DuelistMetrics.Server.controllers.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.util.*;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.io.*;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.logging.*;

public class BundleProcessor {



  public static void parseFolder(String folderPath, boolean saveTopBundles, boolean saveRunsAndInfos, boolean gpi) {
    if (saveTopBundles || saveRunsAndInfos) {
      Logger.getGlobal().info("Reading all files in src/main/resources/runs...");
      ArrayList<TopBundle> bundles = readIn(folderPath);
      Logger.getGlobal().info(bundles.size() + " run files read in for processing.");
      if (getUserInput("Would you like to process " + bundles.size() + " run files? [Y/N]: ", "y")) {
        if (getUserInput("Would you like to process ALL files in one go? [Y/N]: ","y")) {
          gpi(gpi);
          for (TopBundle run : bundles) { parse(run, saveTopBundles, saveRunsAndInfos); }
          Logger.getGlobal().info("Finished processing for " + bundles.size() + " run files");
        }
        else {
          gpi(gpi);
          int filesProc = handleIntermittentProcess(bundles, saveTopBundles, saveRunsAndInfos, gpi);
          Logger.getGlobal().info("Finished processing for " + filesProc + " run files");
        }
      }
    }
    else { gpi(gpi); Logger.getGlobal().info("Skipping runs folder check"); }
  }

  public static void gpi(boolean gpi) {
    if (gpi) {
      long secs = generatePickInfos();
      Logger.getGlobal().info("Generated PickInfos in " + secs + " seconds");
    } else {
      Logger.getGlobal().info("Did not generate PickInfos.");
    }
  }

  public static void parse(TopBundle bnd, boolean saveTopBundles, boolean saveRunsAndInfos) {
    if (saveTopBundles || saveRunsAndInfos) {
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

      PickInfo info = null;
      boolean victory = bnd.getEvent().getVictory();
      Integer ascensionLvl = bnd.getEvent().getAscension_level();
      Integer challengeLvl = bnd.getEvent().getChallenge_level();
      if (challengeLvl == null) { challengeLvl = -1; }
      String deck;
      if (bnd.getEvent().getStarting_deck() != null) {
        deck = bnd.getEvent().getStarting_deck();
      } else {
        deck = "NotYugi";
      }
      String runID = "run #" + bnd.getEvent().getPlay_id();
      Logger.getGlobal().info("Attempting to parse and save " + runID);
      // Parse the information we are interested in from cards/relics/potions/neow bonuses
      // Parsed info is saved into passed in maps
      parseCards(bnd, offered, picked, pickedVic, com, victory);
      parseRelics(bnd, pickedR, pickedVicR, com, victory);
      parsePotions(bnd, pickedP, pickedVicP, com, victory);
      parseNeow(bnd, pickedN, pickedVicN, victory);

      // Find or create info model to represent the state of the run (ascension/challenge/starting deck)
      info = getPinfo(deck, ascensionLvl, challengeLvl);

      // Update info model with processed run info
      processCards(info, offered, picked, pickedVic);
      processRelics(info, pickedR, pickedVicR);
      processPotions(info, pickedP, pickedVicP);
      processNeow(info, pickedN, pickedVicN);

      // Save all to DB
      saveParsedInfo(info, bnd, deck, ascensionLvl, challengeLvl, runID, saveTopBundles, saveRunsAndInfos);
    } else {
      Logger.getGlobal().warning("Nothing to save!");
    }
  }

  private static void saveParsedInfo(PickInfo info, TopBundle bnd, String deck, int asc, int chal, String runID, boolean saveTopBundles, boolean saveRunsAndInfos) {
    if (saveRunsAndInfos || saveTopBundles) {
      Logger.getGlobal().info("Parsed " + runID + ". Attempting to save to DB...");
      if (saveRunsAndInfos) {
        if (info != null) {
          InfoController.getService().create(info);
          Logger.getGlobal().info("PickInfo saved");
        }
        Boolean kaiba = bnd.getEvent().getPlaying_as_kaiba();
        String killedBy = bnd.getEvent().getKilled_by();
        if (kaiba == null) { kaiba = false; }
        if (killedBy == null || killedBy.equals(" ")) { killedBy = "Self"; }
        SimpleDateFormat timestampFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String finalTimeStamp = "Unknown";
        try {
          Date reDated = timestampFormatter.parse(bnd.getEvent().getLocal_time());
          String firstFormat = "EEEEE, MMMMM";
          String dayFormat = "d";
          String endFormat = ", yyyy @ h:mm:ss a";
          SimpleDateFormat frstFormat = new SimpleDateFormat(firstFormat);
          SimpleDateFormat dyFormat = new SimpleDateFormat(dayFormat);
          SimpleDateFormat enFormat = new SimpleDateFormat(endFormat);
          String firstPart = frstFormat.format(reDated);
          String dayPart = dyFormat.format(reDated);
          String ending = enFormat.format(reDated);
          dayPart += LocalProccesor.getDayOfMonthSuffix(Integer.parseInt(dayPart));
          finalTimeStamp = firstPart + " " + dayPart + ending;
        } catch (ParseException ignored) {}
        RunLog log = new RunLogBuilder()
          .setAscension(asc)
          .setChallenge(chal)
          .setDeck(deck)
          .setFloor(bnd.getEvent().getFloor_reached())
          .setHost(bnd.getHost())
          .setKaiba(kaiba)
          .setKilledBy(killedBy)
          .setVictory(bnd.getEvent().getVictory())
          .setTime(finalTimeStamp)
          .setCharacter(bnd.getEvent().getCharacter_chosen())
          .setCountry(bnd.getEvent().getCountry())
          .setLanguage(bnd.getEvent().getLang())
          .setFilterDate(bnd.getEvent().getLocal_time())
          .createRunLog();
        RunLogController.getService().create(log);
        Logger.getGlobal().info("RunLog saved");
      }
      if (saveTopBundles) {
        bnd.getEvent().updateChildren();
        bnd.getEvent().removeDisallowedRelics();
        TopBundle top = BundleController.getService().create(bnd);
        Logger.getGlobal().info("TopBundle " + top.getTop_id() + " saved");
      }
      Logger.getGlobal().info("Full run data has been added to DB for " + runID);
    } else {
      Logger.getGlobal().warning("Nothing to save!");
    }
  }

  private static PickInfo getPinfo(String deck, int asc, int chal) {
    PickInfo info = InfoController.getService().findInfo(deck, asc, chal);
    if (info == null) { info = new PickInfo(deck, asc, chal); }
    return info;
  }

  private static void parseCards(TopBundle bnd, Map<String, Integer> offered, Map<String, Integer> picked, Map<String, Integer> pickedVic, Mapper<String> com, boolean vic) {
    for (SpireCard c : bnd.getEvent().getCard_choices()) {
      String pick = c.getPicked();
      if (!pick.equals("SKIP") && !pick.equals("Singing Bowl")) {
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
      if (vic && !pick.equals("SKIP") && !pick.equals("Singing Bowl")) { pickedVic.compute(pick, com.mp()); }
    }
  }

  private static void parseRelics(TopBundle bnd, Map<String,Integer> pickedR, Map<String, Integer> pickedVicR, Mapper<String> com, boolean vic) {
    for (String r : bnd.getEvent().getRelics()) {
      if (RelicFilter.getInstance().allowed(r)) {
        pickedR.compute(r, com.mp());
        if (vic) { pickedVicR.compute(r, com.mp()); }
      }
    }
  }

  private static void parsePotions(TopBundle bnd, Map<String,Integer> pickedP, Map<String, Integer> pickedVicP, Mapper<String> com, boolean vic) {
    for (Potion p : bnd.getEvent().getPotions_obtained()) {
      String pick = p.getKey();
      pickedP.compute(pick, com.mp());
      if (vic) { pickedVicP.compute(pick, com.mp()); }
    }
  }

  private static void parseNeow(TopBundle bnd, Map<String,Integer> pickedN, Map<String, Integer> pickedVicN, boolean vic) {
    String neow = bnd.getEvent().getNeow_bonus();
    if (neow == null || neow.equals("")) { neow = "NONE"; }
    pickedN.compute(neow, (k,v) -> (v==null) ? 1 : v+1);
    if (vic) { pickedVicN.compute(neow, (k,v) -> (v==null) ? 1 : v+1); }
  }

  private static void processCards(PickInfo info, Map<String, Integer> offered, Map<String, Integer> picked, Map<String, Integer> pickedVic) {
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
  }

  private static void processRelics(PickInfo info, Map<String, Integer> pickedR, Map<String, Integer> pickedVicR) {
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
  }

  private static void processPotions(PickInfo info, Map<String, Integer> pickedP, Map<String, Integer> pickedVicP) {
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
  }


  private static void processNeow(PickInfo info, Map<String, Integer> pickedN, Map<String, Integer> pickedVicN) {
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
  }

  private static ArrayList<TopBundle> readIn(String path){
    File folder = new File(path);
    File[] listOfFiles = folder.listFiles();
    if (listOfFiles != null) {
      ArrayList<TopBundle> output = new ArrayList<>();
      if (listOfFiles.length > 0) {
        for (File file : listOfFiles) {
          if (file.isFile()) {
            try {
              output.add(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(FileUtils.readFileToString(file), TopBundle.class));
            } catch (IOException ignored) {}
          }
        }
      }
      return output;
    }
    Logger.getGlobal().warning("Did not read in runs properly!");
    return new ArrayList<>();
  }

  private static Boolean getUserInput(String prompt, String passString) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(prompt);
    String userInput = scanner.nextLine();
    return (userInput.toLowerCase().equals(passString.toLowerCase()));
  }

  public static Long generatePickInfos() {
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
    long startTime = System.nanoTime();
    for (String deck : decks) {
      for (int ascension = 0; ascension < 21; ascension++) {
        for (int challenge = -1; challenge < 21; challenge++) {
          PickInfo info = new PickInfo(deck, ascension, challenge);
          InfoController.getService().create(info);
        }
      }
    }
    long endTime = System.nanoTime();
    return TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
  }

  private static Integer handleIntermittentProcess(ArrayList<TopBundle> bundles, boolean saveTopBundles, boolean saveRunsAndInfos, boolean gpi) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("How many files to process between checks to continue? [Defaults to 100]: ");
    String userInput = scanner.nextLine();
    int totalProc = 0;
    int filesToCheckBeforePrompt = 100;
    int currentBundleIndex = 0;
    try { filesToCheckBeforePrompt = Integer.parseInt(userInput); } catch (NumberFormatException ignored) {}
    gpi(gpi);
    int filesLeft = bundles.size();
    boolean cont = true;
    while (cont) {
      int amtChecked = 0;
      while (currentBundleIndex < bundles.size() && filesLeft > 0 && amtChecked < filesToCheckBeforePrompt) {
        parse(bundles.get(currentBundleIndex), saveTopBundles, saveRunsAndInfos);
        amtChecked++;
        filesLeft--;
        currentBundleIndex++;
        totalProc++;
      }
      if (currentBundleIndex < bundles.size() && filesLeft > 0) {
        processingStepCompleteAlert();
        System.out.println("Processed " + (currentBundleIndex) + " files out of " + bundles.size() + ". Would you like to continue? [Y/N]: ");
        userInput = scanner.nextLine();
        cont = userInput.toLowerCase().equals("y");
      } else {
        cont = false;
      }
    }
    return totalProc;
  }

  private static void processingStepCompleteAlert() {
    try {
      Toolkit.getDefaultToolkit().beep();
      Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
      Toolkit.getDefaultToolkit().beep();
      Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000));
      Toolkit.getDefaultToolkit().beep();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static class Mapper<K> {
    BiFunction<K, Integer, Integer> mapper;

    public Mapper() { mapper = (k,v) -> (v==null) ? 1 : v+1; }

    public BiFunction<K,Integer,Integer> mp(int amt) {
      mapper = (k,v) -> (v==null) ? amt : v+amt;
      return mapper; }

    public BiFunction<K,Integer,Integer> mp() { return mapper; }
  }
}


