package com.myproject.learneng.service.impl;

import com.myproject.learneng.dao.RoleJpaRepository;
import com.myproject.learneng.dao.UserJpaRepository;
import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.UserUpdateRequest;
import com.myproject.learneng.exception.CustomErrorType;
import com.myproject.learneng.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImplementation.class);
    private UserJpaRepository userJpaRepository;
    private RoleJpaRepository roleJpaRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImplementation(UserJpaRepository userJpaRepository,
                                     RoleJpaRepository roleJpaRepository,
                                     @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> listAllUsers() {
            logger.info("get all users");
        return userJpaRepository.findAll();
    }

    @Override
    public List<User> listAllUsersWithResult() {
            logger.info("get all users with results");
        return userJpaRepository.listUsersWithResults();
    }

    @Override
    public User getUserById(Long id) {
            logger.info("get user by id = "+id);
        return userJpaRepository.findById(id).orElseThrow(()->new CustomErrorType("user not found"));
    }

    @Override
    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        logger.info("update user "+userUpdateRequest.getUsername());
        User user = userJpaRepository.findById(id).orElseThrow(()->new CustomErrorType("user not found"));
        user.setName(userUpdateRequest.getUsername());
        user.setLastModified(LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        user.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
        return userJpaRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public User findUserByName(final String name) {
            logger.info("find user "+name);
        return userJpaRepository.findByName(name).orElseThrow(
                ()->new CustomErrorType("User with name "+name+" doesn't exist"));
    }

    @Override
    public void deleteUser(Long id) {
            logger.info("delete user with id = "+id);
        userJpaRepository.deleteById(id);
    }



}
