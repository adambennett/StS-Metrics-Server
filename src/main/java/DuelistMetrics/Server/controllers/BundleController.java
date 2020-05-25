package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BundleController {

    private static BundleService bundles;
    private static InfoService info;

    @Autowired
    public BundleController(BundleService service, InfoService inf) { bundles = service; info = inf; }

    public static BundleService getService() { return bundles; }

    @GetMapping("/runCountByCountry")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getBundles() {
        try {
            TreeMap<String, Integer> output = bundles.getCountryCounts();
            SortedSet<Map.Entry<String, Integer>> realOutput = entriesSortedByValues(output);
            return (output.size() > 0) ? new ResponseEntity<>(realOutput, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<>((e1, e2) -> {
            int res = e2.getValue().compareTo(e1.getValue());
            return res != 0 ? res : 1;
        });
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

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
                Optional<ModInfoBundle> fullMod = info.getModInfo(mod.getModID(), mod.getModVersion());
                fullMod.ifPresent(fullModList::add);
            }
            return new ResponseEntity<>(fullModList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
