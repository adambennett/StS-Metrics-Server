package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.builders.*;
import DuelistMetrics.Server.repositories.*;
import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import javax.validation.*;
import java.net.*;
import java.util.*;

@RestController
public class CardController {

    private static CardRepo cards;

    @Autowired
    public CardController(CardRepo card) { cards = card; }

    @GetMapping("/Cards/{deck}")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<DisplayCard> getCards(@PathVariable String deck){
        return null;
    }

    @GetMapping("/Cards")
    @CrossOrigin(origins = {"http://sts-duelist-metrics.herokuapp.com", "http://localhost:4200"})
    public static Collection<DisplayCard> getCards(Pageable pageable){
      Collection<DisplayCard> output = new ArrayList<>();
      Page<OfferCard> all = cards.findAll(pageable);
      for (OfferCard c : all) {
        List<OfferCard> allByName = cards.findCardsByName(c.getName());
        int off = 0;
        int pick = 0;
        int pickV = 0;
        String name = "";
        for (OfferCard ca : allByName) {
          off += ca.getOffered();
          pick += ca.getPicked();
          pickV += ca.getPickVic();
          if (name.equals("")) { name = ca.getName(); }
        }
        double pop = 0.0; if (off > 0) { pop = (double)pick/off; }
        double power = 0.0; if (pick - pickV > 0) { power = (double)pickV/(pick - pickV); }
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
      return output;
    }
}
