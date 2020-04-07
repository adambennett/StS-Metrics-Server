package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class DamageService {

    private static DamageRepo repo;

    @Autowired
    public DamageService(DamageRepo rep) { repo = rep; }

    public static DamageInfo create(DamageInfo damage) {
        return repo.save(damage);
    }

    public static Collection<DamageInfo> findAll() { return repo.findAll(); }

    public static Optional<DamageInfo> findById(long postId) { return repo.findById(postId); }

    public static Boolean delete(long postId)
    {
        repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
