package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.WebsiteDuelistCard;
import DuelistMetrics.Server.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("unused")
@RestController
public class CardController {

  private static InfoService infoService;

  @Autowired
  public CardController(InfoService infoServ) {
    infoService = infoServ;
  }

  @GetMapping("/cards-new")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<WebsiteDuelistCard> getCards(){
    return infoService.getAllDuelistCardsForWebview();
  }

  @GetMapping("/cards-new/{decks}")
  @CrossOrigin(origins = {"https://www.duelistmetrics.com", "https://www.dev.duelistmetrics.com", "https://duelistmetrics.com", "https://dev.duelistmetrics.com", "http://localhost:4200"})
  public static Collection<WebsiteDuelistCard> getCards(@PathVariable String decks){
    if (decks == null || decks.isEmpty()) {
      return new ArrayList<>();
    }
    return infoService.getAllCardsByDeckForWebview(new ArrayList<>(Arrays.asList(decks.split(","))));
  }
}
