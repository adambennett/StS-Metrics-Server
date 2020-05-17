package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.infoModels.*;
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
import java.util.logging.*;

@RestController
public class BundleController {

    private static BundleService bundles;
    private static InfoService info;

    @Autowired
    public BundleController(BundleService service, InfoService inf) { bundles = service; info = inf; }

    public static BundleService getService() { return bundles; }

    @GetMapping("/mods/{id}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getModsFromBundle(@PathVariable Long id) {
        Optional<Bundle> bnd = bundles.findByIdInner(id);
        if (bnd.isPresent()) {
            List<MiniMod> mods = bnd.get().getModList();
            List<ModViewer> fullModList = new ArrayList<>();
            for (MiniMod mod : mods) {
                ModViewer view = new ModViewer(mod.getName(), mod.getModVersion());
                fullModList.add(view);
            }
            return new ResponseEntity<>(fullModList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/fullmods/{id}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getFullModsFromBundle(@PathVariable Long id) {
        Optional<Bundle> bnd = bundles.findByIdInner(id);
        if (bnd.isPresent()) {
            List<MiniMod> mods = bnd.get().getModList();
            List<ModInfoBundle> fullModList = new ArrayList<>();
            for (MiniMod mod : mods) {
                Optional<ModInfoBundle> fullMod = info.getModInfo(mod.getID(), mod.getModVersion());
                fullMod.ifPresent(fullModList::add);
            }
            return new ResponseEntity<>(fullModList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
