package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.models.dto.FormattedKeywordDTO;
import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
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
  private static InfoKeywordRepo keywords;

  @Autowired
  public DisplayObjectController(RelicRepo card, PotionRepo pot, NeowRepo no, InfoKeywordRepo kw) {
    relics = card;
    pots = pot;
    neo = no;
    keywords = kw;
  }

  @GetMapping("/relics")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getRelics(){
    return relics.getAll();
  }

  @GetMapping("/potions")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getPotions(){
    return pots.getAll();
  }

  @GetMapping("/neow")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getNeows(){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : neo.getAll()) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "neow");
  }

  @GetMapping("/keywords/duelist")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<FormattedKeywordDTO> getKeywords(){
    var kw = keywords.getDuelistKeywordListLookup();
    var list = new ArrayList<FormattedKeywordDTO>();
    for (var k : kw) {
      list.add(k.format(", "));
    }
    return list;
  }

  @GetMapping("/relics/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getRelics(@PathVariable String deck){
    return relics.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck));
  }

  @GetMapping("/potions/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getPotions(@PathVariable String deck){
    return pots.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck));
  }

  @GetMapping("/neow/{deck}")
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
        c.setName(c.getUuid());
      } else if (objType.equals("potions")) {
        c.setName(c.getUuid());
      } else {
        c.setName(c.getUuid());
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
