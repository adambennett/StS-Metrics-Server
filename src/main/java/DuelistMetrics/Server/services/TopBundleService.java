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
        boolean found = false;
        if (bundle.getRun() != null) {
            for (Bundle b : BundleController.getService().findAll()) {
                if (b.getPlay_id().equals(bundle.getRun().getPlay_id())) {
                    bundle.setRun(b);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            Bundle bnd = new Bundle();
            bnd.setTop(bundle);
            bundle.setRun(bnd);
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
