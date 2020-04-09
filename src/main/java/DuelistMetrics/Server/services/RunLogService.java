package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class RunLogService {

  private RunLogRepo repo;

  @Autowired
  public RunLogService(RunLogRepo repo) { this.repo = repo; }

  public RunLog create(RunLog run) { return this.repo.save(run); }

  public Collection<RunLog> findAll() { return repo.findAll(); }

  public Page<RunLog> findAllPages(Pageable pageable) { return repo.findAll(pageable); }

  public Optional<RunLog> findById(long infoID) { return this.repo.findById(infoID); }

}
