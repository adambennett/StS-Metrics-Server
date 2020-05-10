package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.services.*;
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
public class InfoController {

    private static InfoService bundles;
    private static Map<String, DuelistCardData> cardData;

    @Autowired
    public InfoController(InfoService service) { bundles = service; fillCardData(); }

    public static InfoService getService() { return bundles; }

    @PostMapping("/carduploads")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> upload(@RequestBody CardInfoList list)
    {
        Optional<CardInfoList> existingData = bundles.getInfo();
        if (list != null && existingData.isPresent()) {
            int amtAdded = existingData.get().addCardsToList(list.getCards());
            int amtNotAdded = list.getCards().size() - amtAdded;
            Logger.getGlobal().info("Added " + amtAdded + " new cards to the Card Info DB. Skipped " + amtNotAdded + " duplicated cards.");
            bundles.createCardInfoList(existingData.get());
            fillCardData();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else if (list != null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    public static Optional<DuelistCardData> getCardData(String game_uuid) {
        if (cardData.containsKey(game_uuid)) {
            return Optional.of(cardData.get(game_uuid));
        }
        return Optional.empty();
    }

    private static void fillCardData() {
        cardData = new HashMap<>();
        Optional<CardInfoList> list = bundles.getInfo();
        list.ifPresent(li -> li.getCards().forEach(data -> cardData.put(data.getGameID(), data)));
        list.ifPresent(cardInfoList -> Logger.getGlobal().info("Card Info DB: " + cardInfoList.getCards().size()));
    }
}
