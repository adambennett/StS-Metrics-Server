package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BundleService {

    private BundleRepo repo;

    @Autowired
    public BundleService(BundleRepo repo) { this.repo = repo; }

    public Bundle create(Bundle bundle) {
        return this.repo.save(bundle);
    }

    public Collection<Bundle> findAll() { return repo.findAll(); }

    public Optional<Bundle> findById(long postId) { return this.repo.findById(postId); }

    public Boolean delete(long postId)
    {
        this.repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
