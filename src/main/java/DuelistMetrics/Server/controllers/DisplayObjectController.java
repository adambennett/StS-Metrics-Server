package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.repositories.*;
import DuelistMetrics.Server.util.*;
import org.springframework.beans.factory.annotation.*;
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
    return sortDuelistObjs(output, "relics");
  }

  @GetMapping("/Potions")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getPotions(){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : pots.getAll()) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "potions");
  }

  @GetMapping("/Neow")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getNeows(){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : neo.getAll()) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "neow");
  }

  @GetMapping("/Relics/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getRelics(@PathVariable String deck){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : relics.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "relics");
  }

  @GetMapping("/Potions/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getPotions(@PathVariable String deck){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : pots.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "potions");
  }

  @GetMapping("/Neow/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getCards(@PathVariable String deck){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : neo.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "neow");
  }

  private static Collection<DisplayObject> sortDuelistObjs(Collection<DisplayObject> output, String objType) {
    Collection<DisplayObject> realOutput = new ArrayList<>();
    Collection<DisplayObject> endOutput = new ArrayList<>();
    for (DisplayObject c : output) {
      if (objType.equals("relics")) {
        Optional<InfoRelic> dbRelic = InfoController.getRelic(c.getUuid());
        if (dbRelic.isPresent()) { c.setName(dbRelic.get().getName()); }
        else { c.setName(c.getUuid()); }
      } else if (objType.equals("potions")) {
        Optional<InfoPotion> dbPotion = InfoController.getPotion(c.getUuid());
        if (dbPotion.isPresent()) { c.setName(dbPotion.get().getName()); }
        else { c.setName(c.getUuid()); }
      }
      if (c.getUuid().length() > 10 && c.getUuid().startsWith("theDuelist")) {
        realOutput.add(c);
      } else {
        endOutput.add(c);
      }
    }
    realOutput.addAll(endOutput);
    return realOutput;
  }

  private static void createDisplayObj(Collection<DisplayObject> output, String s) {
    String[] splice = s.split(",");
    if (splice.length > 2) {
      String name = splice[0];
      int pick = Integer.parseInt(splice[1]);
      int pickVic = Integer.parseInt(splice[2]);
      double power = 0.0;
      if (pick - pickVic > 0) { power = (double)pickVic/(pick - pickVic); }
      DisplayObject obj = new DisplayObjectBuilder()
              .setUuid(name)
              .setPicked(pick)
              .setPickVic(pickVic)
              .setPower(power)
              .createDisplayObject();
      output.add(obj);
    }
  }

}
