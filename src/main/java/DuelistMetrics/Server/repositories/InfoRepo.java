package DuelistMetrics.Server.repositories;

import DuelistMetrics.Server.models.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface InfoRepo extends JpaRepository<PickInfo, Long> {

  @Query("SELECT p FROM PickInfo p WHERE p.deck = :deck AND p.ascension = :asc AND p.challenge_level = :chal")
  PickInfo findInfo(@Param("deck") String deck, @Param("asc") int asc, @Param("chal") int chal);

  @Query("SELECT p FROM PickInfo p WHERE p.deck = :deck")
  Page<PickInfo> findAllByDeck(Pageable page, @Param("deck") String deck);

  @Query("SELECT p FROM PickInfo p WHERE p.ascension = :asc")
  Page<PickInfo> findAllByAsc(Pageable page, @Param("asc") int asc);

  @Query("SELECT p FROM PickInfo p WHERE p.challenge_level = :chal")
  Page<PickInfo> findAllByChallenge(Pageable page, @Param("chal") int chal);

  @Query("SELECT p FROM PickInfo p WHERE p.ascension >= :asc")
  Page<PickInfo> findAllByAtLeastAsc(Pageable page, @Param("asc") int asc);

  @Query("SELECT p FROM PickInfo p WHERE p.challenge_level >= :chal")
  Page<PickInfo> findAllByAtLeastChallenge(Pageable page, @Param("chal") int chal);

  @Query("SELECT MAX(id) FROM PickInfo")
  long getHighestID();

}
