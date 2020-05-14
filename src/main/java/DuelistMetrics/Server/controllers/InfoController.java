package DuelistMetrics.Server.controllers;


import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class InfoController {

    private static InfoService bundles;
   // private static Map<String, DuelistCardData> cardData;

    @Autowired
    public InfoController(InfoService service) {
        bundles = service;
        //fillCardData();
    }

    public static InfoService getService() { return bundles; }

    @GetMapping("allModuleVersions")
    public ResponseEntity<?> getTrackedVersions() {
        List<String> versions = bundles.getAllModuleVersions();
        return new ResponseEntity<>(versions, HttpStatus.OK);
    }

    @PostMapping("/dataupload")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> infoUpload(@RequestBody TopInfoBundle list){
        if (list != null) {
            List<ModInfoBundle> saved = new ArrayList<>(list.getInfo().size());
            for (ModInfoBundle mod : list.getInfo()) {
               // if (!bundles.modExists(mod.getMod_id(), mod.getVersion()).isPresent()) {
                    bundles.createBundle(mod);
                    saved.add(mod);
                /*} else {
                    Logger.getGlobal().info("Skipping upload for " + mod.getModName() + " - " + mod.getVersion() + " because it already existed in the database.");
                }*/
            }
            if (saved.size() > 0) {
                return new ResponseEntity<>(saved, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

   /* private static void fillCardData() {
        cardData = new HashMap<>();
        Optional<CardInfoList> list = bundles.getInfo();
        list.ifPresent(li -> li.getCards().forEach(data -> cardData.put(data.getGameID(), data)));
        list.ifPresent(cardInfoList -> Logger.getGlobal().info("Card Info DB: " + cardInfoList.getCards().size()));
    }*/
}
