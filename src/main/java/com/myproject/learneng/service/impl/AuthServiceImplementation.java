package com.myproject.learneng.service.impl;

import com.myproject.learneng.dao.RoleJpaRepository;
import com.myproject.learneng.dao.UserJpaRepository;
import com.myproject.learneng.domain.Role;
import com.myproject.learneng.domain.RoleName;
import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.AuthenticatedRequest;
import com.myproject.learneng.dto.AuthenticatedResponse;
import com.myproject.learneng.dto.RefreshTokenRequest;
import com.myproject.learneng.exception.CustomErrorType;
import com.myproject.learneng.security.AuthenticatedUser;
import com.myproject.learneng.security.JwtUtil;
import com.myproject.learneng.service.AuthService;
import com.myproject.learneng.service.RefreshTokenService;
import com.myproject.learneng.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImplementation implements AuthService {
    private static final Logger logger = Logger.getLogger(AuthServiceImplementation.class);
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserJpaRepository userJpaRepository;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtil jwtUtil;
    private RefreshTokenService refreshTokenService;
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    public AuthServiceImplementation(BCryptPasswordEncoder bCryptPasswordEncoder,
                                     UserJpaRepository userJpaRepository,
                                     AuthenticationManager authenticationManager,
                                     UserService userService,
                                     JwtUtil jwtUtil,
                                     RefreshTokenService refreshTokenService,
                                     RoleJpaRepository roleJpaRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userJpaRepository = userJpaRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public User signup(AuthenticatedRequest registerRequest) {
        Set<Role> roles = new HashSet<>();
        Role roleName = roleJpaRepository.findByRoleName(RoleName.ROLE_USER);
        roles.add(roleName);

        User user = new User();
        user.setName(registerRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setLastModified(LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        user.setRoles(roles);
        logger.info("create user with name " + registerRequest.getUsername());
        return userJpaRepository.save(user);
    }

    @Override
    public AuthenticatedResponse login(AuthenticatedRequest loginRequest) {
        User user = userService.findUserByName(loginRequest.getUsername());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                                                                     loginRequest.getPassword());
        Authentication authenticatedUser = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        String token = jwtUtil.createToken(user.getName(), user.getRoles());
        String refreshToken = refreshTokenService.generateRefreshToken().getToken();
        String validity = LocalDateTime.now((ZoneId.of("Europe/Moscow"))).
                plusSeconds(jwtUtil.getExpirationTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        AuthenticatedResponse authenticatedResponse = new AuthenticatedResponse(token, validity, user.getName(), refreshToken);
        logger.info("login user");
        return authenticatedResponse;

    }

    @Override
    public User getCurrentUser() {
        AuthenticatedUser userDetails = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetails.getUsername();
        logger.info("try get current user");
        User user = userJpaRepository.findByName(name)
                .orElseThrow(() -> new CustomErrorType("User not found"));
        return user;
    }

    @Override
    public AuthenticatedResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getToken());
        User user = userService.findUserByName(refreshTokenRequest.getUsername());
        String token = jwtUtil.createToken(user.getName(), user.getRoles());
        String refreshToken = refreshTokenRequest.getToken();
        String validity = LocalDateTime.now((ZoneId.of("Europe/Moscow"))).
                plusSeconds(jwtUtil.getExpirationTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        AuthenticatedResponse authenticatedResponse = new AuthenticatedResponse(token, refreshToken, validity, refreshTokenRequest.getUsername());
        logger.info("refresh token in" + LocalDateTime.now((ZoneId.of("Europe/Moscow"))).
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "valid until " + validity);
        return authenticatedResponse;
    }
}
