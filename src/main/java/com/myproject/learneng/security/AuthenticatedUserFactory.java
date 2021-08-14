package com.myproject.learneng.security;

import com.myproject.learneng.domain.Role;
import com.myproject.learneng.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthenticatedUserFactory {
    public AuthenticatedUserFactory() {}

    public static AuthenticatedUser create(User user){
        return new AuthenticatedUser(user.getId(),
                user.getName(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()));
    }

    private static Set<GrantedAuthority> mapToGrantedAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toSet());
    }
}