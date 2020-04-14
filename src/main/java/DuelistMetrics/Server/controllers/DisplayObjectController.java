package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.repositories.*;
import DuelistMetrics.Server.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class DisplayObjectController {

  private static RelicRepo relics;
  private static PotionRepo pots;
  private static NeowRepo neo;

  @Autowired
  public DisplayObjectController(RelicRepo card, PotionRepo pot, NeowRepo no) {
    relics = card;
    pots = pot;
    neo = no;
  }

  @GetMapping("/Relics")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getRelics(){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : relics.getAll()) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output);
  }

  @GetMapping("/Potions")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getPotions(){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : pots.getAll()) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output);
  }

  @GetMapping("/Neow")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getNeows(){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : neo.getAll()) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output);
  }

  private static Collection<DisplayObject> sortDuelistObjs(Collection<DisplayObject> output) {
    Collection<DisplayObject> realOutput = new ArrayList<>();
    Collection<DisplayObject> endOutput = new ArrayList<>();
    for (DisplayObject c : output) {
      if (c.getName().length() > 10 && c.getName().startsWith("theDuelist")) {
        realOutput.add(c);
      } else {
        endOutput.add(c);
      }
    }
    realOutput.addAll(endOutput);
    return realOutput;
  }

  @GetMapping("/Relics/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getRelics(@PathVariable String deck){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : relics.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output);
  }

  @GetMapping("/Potions/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getPotions(@PathVariable String deck){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : pots.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output);
  }

  @GetMapping("/Neow/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getCards(@PathVariable String deck){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : neo.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output);
  }

  private static void createDisplayObj(Collection<DisplayObject> output, String s) {}

  private static void createDisplayCard(Collection<DisplayCard> output, String s) {
    String[] splice = s.split(",");
    if (splice.length > 3) {
      String name = splice[0];
      int off = Integer.parseInt(splice[1]);
      int pick = Integer.parseInt(splice[2]);
      int pickV = Integer.parseInt(splice[3]);
      double pop = 0.0;
      if (off > 0) { pop = (double)pick/off; }
      double power = 0.0;
      if (pick - pickV > 0) { power = (double)pickV/(pick - pickV); }
      DisplayCard ca = new DisplayCardBuilder()
        .setName(name)
        .setOffered(off)
        .setPicked(pick)
        .setPickVic(pickV)
        .setPopularity(pop)
        .setPower(power)
        .createDisplayCard();
      output.add(ca);
    }
  }

}
