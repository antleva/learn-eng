package com.myproject.learneng.controllers;

import com.myproject.learneng.domain.Role;
import com.myproject.learneng.domain.RoleName;
import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.UserDto;
import com.myproject.learneng.dto.UserUpdateRequest;
import com.myproject.learneng.exception.CustomErrorType;
import com.myproject.learneng.service.AuthService;
import com.myproject.learneng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthService authService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,AuthService authService) {
        this.userService = userService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.listAllUsers().stream().map(UserDto::getUserDtoWithResults).collect(Collectors.toList());
        return new ResponseEntity<>(users,OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        UserDto userDto=null;
            if(user!=null) {
                userDto = UserDto.getUserDto(user);
            }else throw new CustomErrorType("user with id = "+id+" not found");
        return new ResponseEntity<>(userDto,OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<List<UserDto>> deleteUser(@PathVariable Long id){
        User current = authService.getCurrentUser();
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        if(current.getRoles().contains(roleAdmin)){
            userService.deleteUser(id);
        }
        List<UserDto> users = userService.listAllUsers().stream().map(UserDto::getUserDto).collect(Collectors.toList());
        return new ResponseEntity<>(users,OK);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest){
        User user = userService.updateUser(id, userUpdateRequest);
        return new ResponseEntity<>(UserDto.getUserDto(user),OK);
    }
    @GetMapping("/results")
    public ResponseEntity<List<UserDto>> getAllUsersResults(){
        List<User> userList = userService.listAllUsersWithResult();
        List<UserDto> userDtoList = userList.stream().map(UserDto::getUserDtoWithResults).collect(Collectors.toList());
        return new ResponseEntity<>(userDtoList,OK);
    }
}
