package com.myproject.learneng.dao;

import com.myproject.learneng.domain.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Long> {

    Optional<Phrase> findByDescription(String description);

    List<Phrase> findAll();

    @Query(value = "select * from PHRASE p where p.PHRASE_TYPE = 'IDIOM'", nativeQuery = true)
    Set<Phrase> listIdioms();

    @Query(value = "select * from PHRASE p where p.PHRASE_TYPE = 'PHRASAL_VERB'", nativeQuery = true)
    Set<Phrase> listPhrasalVerbs();

    @Query(value = "select * from PHRASE p inner join USERS_PHRASE up on p.PHRASE_TYPE = 'PHRASAL_VERB' " +
            "and up.USER_ID = ?1", nativeQuery = true)
    Set<Phrase> listUsersPhrasalVerbs(Long id);

    @Query(value = "select * from PHRASE p inner join USERS_PHRASE up on p.PHRASE_TYPE = 'IDIOM'  " +
            "and up.USER_ID = ?1", nativeQuery = true)
    Set<Phrase> listUsersIdioms(Long id);


}
