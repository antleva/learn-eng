package com.myproject.learneng.dto;

import com.myproject.learneng.domain.Result;
import com.myproject.learneng.domain.Role;
import com.myproject.learneng.domain.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {
    private String id;
    private String name;
    private String password;
    private String creationDate;
    private String lastModifiedDate;
    private Set<String> roles;
    private List<String> results;

    public UserDto(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.password = user.getPassword();
        this.creationDate = user.getCreatedDate().toString();
        this.lastModifiedDate = user.getLastModified().toString();
        this.roles = mapRolesToString(user.getRoles());
    }

    public static UserDto getUserDto(User user){
        return new UserDto(user);
    }

    public static UserDto getUserDtoWithResults(User user){
        UserDto userDto = new UserDto(user);
        userDto.setResults(mapResultToDto(user.getSetResults()));
        return userDto;
    }

    public static Set<String> mapRolesToString(Set<Role> roleSet){
        return roleSet.stream().map(role -> role.getRoleName().name()).collect(Collectors.toSet());
    }

    public static List<String> mapResultToDto(List<Result> resultSet){
        return resultSet.stream().map(ResultDto::getResultDto).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
