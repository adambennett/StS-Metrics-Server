package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BundleService {

  private BundleRepo repo;

  @Autowired
  public BundleService(BundleRepo repo) { this.repo = repo; }

  public TopBundle create(TopBundle run) { return this.repo.save(run); }

  public Collection<TopBundle> findAll() { return repo.findAll(); }

  public Page<TopBundle> findAllPages(Pageable pageable) { return repo.findAll(pageable); }

  public Optional<TopBundle> findById(long infoID) { return this.repo.findById(infoID); }

}
