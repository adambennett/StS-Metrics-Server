package DuelistMetrics.Server.models;

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


  public static void temp() throws IOException {
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
        List<String> notPicked = c.getNot_picked();
        offered.compute(pick, com.mp());
        picked.compute(pick, com.mp());
        for (String s : notPicked) { offered.compute(s, com.mp()); }
        if (vic) { pickedVic.compute(pick, com.mp()); }
      }

      for (String r : bnd.getEvent().getRelics()) {
        pickedR.compute(r, com.mp());
        if (vic) { pickedVicR.compute(r, com.mp()); }
      }

      for (BossRelic r : bnd.getEvent().getBoss_relics()) {
        String pick = r.getPicked();
        pickedR.compute(pick, com.mp());
        if (vic) { pickedVicR.compute(pick, com.mp()); }
      }

      for (Potion p : bnd.getEvent().getPotions_obtained()) {
        String pick = p.getKey();
        pickedP.compute(pick, com.mp());
        if (vic) { pickedVicP.compute(pick, com.mp()); }
      }

      String neow = bnd.getEvent().getNeow_bonus();
      pickedN.compute(neow, (k,v) -> (v==null) ? 1 : v+1);
      if (vic) { pickedVicN.compute(neow, (k,v) -> (v==null) ? 1 : v+1); }

      PickInfo info = new PickInfo(deck, asc, chal);
      //TODO: Check to see if this exists already, if so replace this object with reference to exisiting Pinfo
      for (Map.Entry<String, Integer> entry : offered.entrySet()) {
        OfferCard offer = new OfferCard(entry.getKey(), entry.getValue(), 0, 0, info);
        info.addCard(offer);
      }
      for (Map.Entry<String, Integer> entry : picked.entrySet()) {
        for (OfferCard c : info.getCards()) {
          if (c.getName().equals(entry.getKey())) {
            c.setPicked(entry.getValue());
            break;
          }
        }
      }
      for (Map.Entry<String, Integer> entry : pickedVic.entrySet()) {
        for (OfferCard c : info.getCards()) {
          if (c.getName().equals(entry.getKey())) {
            c.setPickVic(entry.getValue());
            break;
          }
        }
      }
      for (Map.Entry<String, Integer> entry : pickedR.entrySet()) {

      }
      for (Map.Entry<String, Integer> entry : pickedP.entrySet()) {

      }
      for (Map.Entry<String, Integer> entry : pickedN.entrySet()) {

      }
      for (Map.Entry<String, Integer> entry : pickedVicR.entrySet()) {

      }
      for (Map.Entry<String, Integer> entry : pickedVicP.entrySet()) {

      }
      for (Map.Entry<String, Integer> entry : pickedVicN.entrySet()) {

      }
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
