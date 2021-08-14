package com.myproject.learneng.dao;

import com.myproject.learneng.domain.Result;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
class UserJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserJpaRepository userRepository;

    private User user;

    @BeforeEach
    public void init(){
        user = new User("User","123");
        entityManager.persist(user);
        entityManager.flush();
    }

    @AfterEach
    public void disable(){
        entityManager.remove(user);
        entityManager.flush();
    }

    @Test
    public void whenFindByNameThenReturnUser() {
        Optional<User> found = userRepository.findByName("User");
        User actual = found.get();

        assertNotNull(actual);
        assertEquals("User", actual.getName());
    }

    @Test
    public void whenFindByIdThenReturnUser() {
        Optional<User> found = userRepository.findById(2l);
        User actual = found.get();

        assertNotNull(actual);
        assertEquals(2, actual.getId());
    }

    @Test
    public void whenFindListUsersWithResultsThenReturnSize() {
        Result result = new Result(10,5,5,50);
        User anotherUser = new User("User_2","123");
        user.setSetResults(List.of(result));
        anotherUser.setSetResults(List.of(result));
        entityManager.persist(anotherUser);
        entityManager.flush();

        List<User> found = userRepository.listUsersWithResults();
        assertEquals(2,found.size());

        entityManager.remove(anotherUser);
    }

}