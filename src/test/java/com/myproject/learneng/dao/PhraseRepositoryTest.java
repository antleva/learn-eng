package com.myproject.learneng.dao;

import com.myproject.learneng.domain.Phrase;
import com.myproject.learneng.domain.PhraseType;
import com.myproject.learneng.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class PhraseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PhraseRepository phraseRepository;

    private  Phrase phrase;

    private  User user;

    @BeforeEach
    public void init(){
        user = new User("User","123");
        phrase = new Phrase(PhraseType.PHRASAL_VERB,"get up","встать");
        user.setSetPhrases(Set.of(phrase));
        entityManager.persist(phrase);
        entityManager.persist(user);
        entityManager.flush();
    }

    @AfterEach
    public void disable(){
        entityManager.remove(phrase);
        entityManager.remove(user);
        entityManager.flush();
    }

    @Test
    public void whenFindByDescriptionThenReturnPhrase() {
        Optional<Phrase> found = phraseRepository.findByDescription(phrase.getDescription());
        Phrase actual = found.get();

        assertNotNull(actual);
        assertEquals("get up", actual.getDescription());
    }

    @Test
    public void whenFindByIdThenReturnPhrase(){
        Optional<Phrase> found = phraseRepository.findById(phrase.getId());
        Phrase actual = found.get();

        assertNotNull(actual);
        assertEquals(1, actual.getId());
    }

    @Test
    public void whenFindListPhrasalVerbsThenReturnSize(){
        Set<Phrase> found = phraseRepository.listPhrasalVerbs();

        assertEquals(1, found.size());
    }

    @Test
    public void whenFindListUsersPhrasalVerbsThenReturnSize(){
        Set<Phrase> found = phraseRepository.listUsersPhrasalVerbs(user.getId());

        assertEquals(1, found.size());
    }

    @Test
    public void whenFindListUsersIdiomsThenReturnSize(){
        Set<Phrase> found = phraseRepository.listUsersIdioms(user.getId());

        assertEquals(0, found.size());
    }

    @Test
    public void whenFindAllReturnSize(){
        List<Phrase> found = phraseRepository.findAll();

        assertEquals(1, found.size());
    }

}