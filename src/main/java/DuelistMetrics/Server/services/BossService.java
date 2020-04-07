package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BossService {

    private static BossRepo repo;

    @Autowired
    public BossService(BossRepo rep) { repo = rep; }

    public static BossRelic create(BossRelic relic) {
        return repo.save(relic);
    }

    public static Collection<BossRelic> findAll() { return repo.findAll(); }

    public static Optional<BossRelic> findById(long postId) { return repo.findById(postId); }

    public static Boolean delete(long postId)
    {
        repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
