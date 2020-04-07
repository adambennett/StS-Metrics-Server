package DuelistMetrics.Server.services;

import DuelistMetrics.Server.controllers.*;
import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TopBundleService {

  private TopBundleRepo repo;

  @Autowired
  public TopBundleService(TopBundleRepo repo) { this.repo = repo; }

  public TopBundle create(TopBundle bundle) {
    if (bundle.getEvent() != null) {
      bundle.getEvent().updateChildren();
      for (BossRelic r : bundle.getEvent().getBoss_relics()) {
        if (r.getBundle() == null) {
          System.out.println("HOW!");
        }
      }
    }
    return this.repo.save(bundle);
  }

  public Collection<TopBundle> findAll() { return repo.findAll(); }

  public Optional<TopBundle> findById(long postId) { return this.repo.findById(postId); }

  public Boolean delete(long postId)
  {
    this.repo.deleteById(postId);
    return findById(postId).isPresent();
  }

}
