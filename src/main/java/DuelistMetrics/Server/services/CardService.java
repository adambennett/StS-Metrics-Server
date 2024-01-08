package DuelistMetrics.Server.services;

import DuelistMetrics.Server.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CardService {

    private CardRepo repo;

    @Autowired
    public CardService(CardRepo repo) { this.repo = repo; }

    public List<String> getAll() { return repo.getAll("NotYugi"); }
}
