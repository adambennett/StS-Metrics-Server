package DuelistMetrics.Server.services;

import DuelistMetrics.Server.models.*;
import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class EventService {

    private static EventRepo repo;

    @Autowired
    public EventService(EventRepo rep) { repo = rep; }

    public static Event create(Event event) {
        return repo.save(event);
    }

    public static Collection<Event> findAll() { return repo.findAll(); }

    public static Optional<Event> findById(long postId) { return repo.findById(postId); }

    public static Boolean delete(long postId)
    {
        repo.deleteById(postId);
        return findById(postId).isPresent();
    }

}
