package com.myproject.learneng.dao;

import com.myproject.learneng.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);

    @Query(value = "select distinct u from User u join fetch u.setResults sr")
    List<User> listUsersWithResults();

}
