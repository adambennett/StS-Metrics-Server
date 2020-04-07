package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PotionService {

    private static PotionRepo repo;

    @Autowired
    public PotionService(PotionRepo rep) { repo = rep; }

    public static Potion create(Potion potion) {
        return repo.save(potion);
    }

    public static Collection<Potion> findAll() { return repo.findAll(); }

    public static Optional<Potion> findById(long postId) { return repo.findById(postId); }

    public static Boolean delete(long postId)
    {
        repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
