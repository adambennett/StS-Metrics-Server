package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CardService {

    private static CardRepo repo;

    @Autowired
    public CardService(CardRepo rep) { repo = rep; }

    public static SpireCard create(SpireCard card) {
        return repo.save(card);
    }

    public static Collection<SpireCard> findAll() { return repo.findAll(); }

    public static Optional<SpireCard> findById(long postId) { return repo.findById(postId); }

    public static Boolean delete(long postId)
    {
        repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
