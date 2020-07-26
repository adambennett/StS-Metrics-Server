package DuelistMetrics.Server.services;

import DuelistMetrics.Server.controllers.*;
import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BundleService {

  private TopBundleRepo repo;
  private BundleRepo innerRepo;

  @Autowired
  public BundleService(TopBundleRepo repo, BundleRepo inner) { this.repo = repo; this.innerRepo = inner; }

  public TopBundle create(TopBundle run) { return this.repo.save(run); }

  public Collection<TopBundle> findAll() { return repo.findAll(); }

  public Collection<Bundle> findAllInner() { return innerRepo.findAll(); }

  public Page<TopBundle> findAllPages(Pageable pageable) { return repo.findAll(pageable); }

  public Optional<TopBundle> findById(long infoID) { return this.repo.findById(infoID); }

  public Optional<Bundle> findByIdInner(long ID) { return this.innerRepo.findById(ID); }

  public List<String> getCountries() {
    return innerRepo.getCountries();
  }

  public TreeMap<String, Integer> getCountryCounts() {
    List<String> query = innerRepo.getCountryCounts();
    TreeMap<String, Integer> out = new TreeMap<>();
    for (String s : query) {
      String[] splice = s.split(",");
      String country = splice[0];
      Locale locale = new Locale("", country);
      String displayCountry = locale.getDisplayCountry();
      try {
        Integer num = Integer.parseInt(splice[1]);
        out.compute(displayCountry, (k,v) -> (v==null) ? num : v+num);
      } catch (NumberFormatException ignored) {
        out.compute(displayCountry, (k,v) -> -99);
      }
    }
    return out;
  }

  public Map<String, String> getCountryNameAndID() {
    List<String> query  = innerRepo.getCountryCounts();
    Map<String, String> output = new HashMap<>();
    LinkedHashMap<String, String> sortedOutput = new LinkedHashMap<>();
    for (String s : query) {
      String[] splice = s.split(",");
      String country = splice[0];
      String displayCountry = new Locale("", country).getDisplayCountry();
      output.put(displayCountry, country);
    }
    TreeMap<String, Integer> sorted = getCountryCounts();
    SortedSet<Map.Entry<String, Integer>> realSorted = BundleController.entriesSortedByValues(sorted);
    for (Map.Entry<String, Integer> entry : realSorted) {
      sortedOutput.put(entry.getKey(), output.get(entry.getKey()));
    }
    return sortedOutput;
  }

}
