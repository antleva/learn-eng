package com.myproject.learneng.service;

import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    List<User> listAllUsers();

    List<User> listAllUsersWithResult();

    User getUserById(Long id);

    User updateUser(Long id, UserUpdateRequest user);

    User updateUser(User user);

    User findUserByName(String name);

    void deleteUser(Long id);
}

