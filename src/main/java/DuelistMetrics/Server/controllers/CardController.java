package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.repositories.*;
import DuelistMetrics.Server.services.*;
import DuelistMetrics.Server.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import javax.validation.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

@RestController
public class CardController {

  private static CardRepo cards;
  private static CardService serv;
  private static InfoCardRepo infoCardRepo;

  @Autowired
  public CardController(CardRepo card, InfoCardRepo infoRepo, CardService service) { cards = card; infoCardRepo = infoRepo; serv = service; }

  @GetMapping("/cards")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayCard> getCards(){
    Collection<DisplayCard> output = new ArrayList<>();
    for (String s : serv.getAll()) {
      createDisplayCard(output, s);
    }
    return sortDuelistCards(output);
  }

  @GetMapping("/cards/{deck}")
  @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
  public static Collection<DisplayCard> getCards(@PathVariable String deck){
    Collection<DisplayCard> output = new ArrayList<>();
    for (String s : cards.getAllFromDeck(DeckNameProcessor.getProperDeckName(deck))) {
      createDisplayCard(output, s);
    }
    return sortDuelistCards(output);
  }

  private static Collection<DisplayCard> sortDuelistCards(Collection<DisplayCard> output) {
    Collection<DisplayCard> realOutput = new ArrayList<>();
    Collection<DisplayCard> endOutput = new ArrayList<>();
    for (DisplayCard c : output) {
      Optional<InfoCard> dbCard = InfoController.getCard(c.getUuid());
      if (dbCard.isPresent()) { c.setName(dbCard.get().getName()); }
      else { c.setName(c.getUuid()); }
      if (c.getUuid().length() > 10 && c.getUuid().startsWith("theDuelist")) {
        realOutput.add(c);
      } else {
        endOutput.add(c);
      }
    }
    realOutput.addAll(endOutput);
    return realOutput;
  }

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
        .setUuid(name)
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
