package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.interfaces.InfoObjectLookupQuery;
import DuelistMetrics.Server.models.DisplayObject;
import DuelistMetrics.Server.models.builders.DisplayObjectBuilder;
import DuelistMetrics.Server.models.dto.FormattedKeywordDTO;
import DuelistMetrics.Server.models.dto.FullInfoDisplayObject;
import DuelistMetrics.Server.repositories.InfoKeywordRepo;
import DuelistMetrics.Server.repositories.NeowRepo;
import DuelistMetrics.Server.repositories.PotionRepo;
import DuelistMetrics.Server.repositories.RelicRepo;
import DuelistMetrics.Server.util.DeckNameProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
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
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getRelics(){
    List<String> relicIds = relics.getRelicIdsForInfoObjectLookups();
    List<FullInfoDisplayObject> offerData = relics.getAll(relicIds);
    return mergeInfoWithOfferData(offerData, relics::getInfoRelicData);
  }

  @GetMapping("/potions")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getPotions(){
    List<String> potionIds = pots.getPotionIdsForInfoObjectLookups();
    List<FullInfoDisplayObject> offerData = pots.getAll(potionIds);
    return mergeInfoWithOfferData(offerData, pots::getInfoPotionData);
  }

  @GetMapping("/neow")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getNeows(){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : neo.getAll()) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "neow");
  }

  @GetMapping("/keywords/duelist")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<FormattedKeywordDTO> getKeywords(){
    var kw = keywords.getDuelistKeywordListLookup();
    var list = new ArrayList<FormattedKeywordDTO>();
    for (var k : kw) {
      list.add(k.format(", "));
    }
    return list;
  }

  @GetMapping("/relics/{deck}")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getRelics(@PathVariable String deck){
    List<String> relicIds = relics.getRelicIdsForInfoObjectLookups();
    String properDeck = DeckNameProcessor.getProperDeckName(deck);
    List<FullInfoDisplayObject> offerData = relics.getAllFromDeck(properDeck, relicIds);
    return mergeInfoWithOfferData(offerData, relics::getInfoRelicData);
  }

  @GetMapping("/potions/{deck}")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<FullInfoDisplayObject> getPotions(@PathVariable String deck){
    List<String> potionIds = pots.getPotionIdsForInfoObjectLookups();
    String properDeck = DeckNameProcessor.getProperDeckName(deck);
    List<FullInfoDisplayObject> offerData = pots.getAllFromDeck(properDeck, potionIds);
    return mergeInfoWithOfferData(offerData, pots::getInfoPotionData);
  }

  @GetMapping("/neow/{deck}")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<DisplayObject> getCards(@PathVariable String deck){
    Collection<DisplayObject> output = new ArrayList<>();
    for (String s : neo.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayObj(output, s);
    }
    return sortDuelistObjs(output, "neow");
  }

  private static Collection<FullInfoDisplayObject> mergeInfoWithOfferData(List<FullInfoDisplayObject> initialOutput, InfoObjectLookupQuery query) {
    Map<String, List<FullInfoDisplayObject>> infoOutput = query.getInfoData(initialOutput.stream().map(FullInfoDisplayObject::uuid).toList()).stream().collect(Collectors.groupingBy(FullInfoDisplayObject::uuid));
    List<FullInfoDisplayObject> merged = new ArrayList<>();
    for (FullInfoDisplayObject initial : initialOutput) {
      if (infoOutput.containsKey(initial.uuid())) {
        List<FullInfoDisplayObject> infos = infoOutput.get(initial.uuid());
        if (!infos.isEmpty()) {
          FullInfoDisplayObject info = infos.getFirst();
          merged.add(new FullInfoDisplayObject(initial.uuid(), info.name(), info.rarity(), info.description(), info.flavor(), initial.picked(), initial.pickedVictory(), initial.power(), initial.type()));
        }
      }
    }
    return merged;
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
