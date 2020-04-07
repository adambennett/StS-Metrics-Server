package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class RelicService {

    private static RelicRepo repo;

    @Autowired
    public RelicService(RelicRepo rep) { repo = rep; }

    public static Relic create(Relic relic) {
        return repo.save(relic);
    }

    public static Collection<Relic> findAll() { return repo.findAll(); }

    public static Optional<Relic> findById(long postId) { return repo.findById(postId); }

    public static Boolean delete(long postId)
    {
        repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
