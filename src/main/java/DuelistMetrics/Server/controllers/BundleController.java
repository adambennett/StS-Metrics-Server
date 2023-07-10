package DuelistMetrics.Server.controllers;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.models.infoModels.*;
import DuelistMetrics.Server.services.*;
import DuelistMetrics.Server.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.logging.*;

@RestController
public class BundleController {

    private static BundleService bundles;
    private static InfoService info;

    private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.BundleController");

    @Autowired
    public BundleController(BundleService service, InfoService inf) { bundles = service; info = inf; }

    public static BundleService getService() { return bundles; }

    @GetMapping("/runCountByCountry")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getBundles() {
        try {
            TreeMap<String, Integer> output = bundles.getCountryCounts();
            SortedSet<Map.Entry<String, Integer>> realOutput = entriesSortedByValues(output);
            List<CountryRunCount> objOut = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : realOutput) {
                objOut.add(new CountryRunCount(entry.getKey(), entry.getValue()));
            }
            return (objOut.size() > 0) ? new ResponseEntity<>(objOut, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/countries")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getCountries() {
        try {
            Map<String, String> query = bundles.getCountryNameAndID();
            List<Country> countries = new ArrayList<>();
            for (Map.Entry<String, String> entry : query.entrySet()) {
                Country country = new Country(entry.getKey(), entry.getValue());
                countries.add(country);
            }
            return (countries.size() > 0) ? new ResponseEntity<>(countries, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
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

    public static List<ModViewer> getModsFromBundle(Long id) {
        Optional<Bundle> bnd = bundles.findByIdInner(id);
        List<ModViewer> fullModList = new ArrayList<>();
        if (bnd.isPresent()) {
            List<MiniMod> mods = bnd.get().getModList();
            for (MiniMod mod : mods) {
                ModViewer view = new ModViewer(mod.getName(), mod.getModVersion());
                fullModList.add(view);
            }
        }
        return fullModList;
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

    private record RunTimeFrameData(Integer runs, String date){}

    @PostMapping("/runs-in-time-frame/{weeks}")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getRunsInTimeFrame(@PathVariable String weeks, @RequestBody RunCountParams params) {
        try {
            boolean isParams = params != null;
            var output = new ArrayList<RunTimeFrameData>();
            var numWeeks = Integer.parseInt(weeks);
            if (numWeeks > 0) {
                var start = 0;
                var end = 168;
                while (numWeeks > 0) {
                    Integer count = countRunsInTimeFrame(params, isParams, start, end);
                    var startDate = bundles.getTimeFrame(start);
                    var endDate = bundles.getTimeFrame(end - 1);
                    var startDateTime = new SimpleDateFormat("MM/dd").format(startDate);
                    var endDateTime = new SimpleDateFormat("MM/dd").format(endDate);
                    var date = endDateTime + " - " + startDateTime;
                    output.add(new RunTimeFrameData(count, date));
                    start += 168;
                    end += 168;
                    numWeeks--;
                }
                return new ResponseEntity<>(output, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.info("Exception fetching run timeframe data\n" + Arrays.toString(ex.getStackTrace()));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/runs-in-week")
    @CrossOrigin(origins = {"https://sts-metrics-site.herokuapp.com", "http://localhost:4200"})
    public ResponseEntity<?> getRunsInOneWeek(@RequestBody RunCountParams params) {
        try {
            boolean isParams = params != null;
            var output = new ArrayList<RunTimeFrameData>();
            var numDays = 7;
            var start = 0;
            var end = 24;
            while (numDays > 0) {
                Integer count = countRunsInTimeFrame(params, isParams, start, end);
                var midDate = bundles.getTimeFrame(start + 12);
                var date = new SimpleDateFormat("EEEE - MM/dd/yyyy").format(midDate);
                output.add(new RunTimeFrameData(count, date));
                start += 24;
                end += 24;
                numDays--;
            }
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception ex) {
            logger.info("Exception fetching run timeframe data for 1 week\n" + Arrays.toString(ex.getStackTrace()));
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private static Integer countRunsInTimeFrame(@RequestBody RunCountParams params, boolean isParams, int start, int end) {
        Integer count;
        if (!isParams || params.noTypes) {
            count = bundles.countRunsInTimeFrame(end, start);
        } else {
            var t = params.types;
            count = bundles.countRunsInTimeFrame(end, start, t.character(), t.duelist(),
                    t.nonDuelist(), t.timeStart(), t.timeEnd(), t.host(), t.country(), t.ascensionStart(),
                    t.ascensionEnd(), t.challengeStart(), t.challengeEnd(), t.victory(), t.floorStart(),
                    t.floorEnd(), t.deck(), t.killedBy());
        }
        return count;
    }
}
