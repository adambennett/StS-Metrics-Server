package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CampfireService {

    private static CampfireRepo repo;

    @Autowired
    public CampfireService(CampfireRepo rep) { repo = rep; }

    public static CampfireChoice create(CampfireChoice choice) {
        return repo.save(choice);
    }

    public static Collection<CampfireChoice> findAll() { return repo.findAll(); }

    public static Optional<CampfireChoice> findById(long postId) { return repo.findById(postId); }

    public static Boolean delete(long postId)
    {
        repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
