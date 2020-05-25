package DuelistMetrics.Server.services;

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

}
