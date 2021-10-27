package com.myproject.learneng.service;

import com.myproject.learneng.dao.RoleJpaRepository;
import com.myproject.learneng.dao.UserJpaRepository;
import com.myproject.learneng.domain.RefreshToken;
import com.myproject.learneng.domain.Role;
import com.myproject.learneng.domain.RoleName;
import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.AuthenticatedRequest;
import com.myproject.learneng.dto.AuthenticatedResponse;
import com.myproject.learneng.security.JwtUtil;
import com.myproject.learneng.service.impl.AuthServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Set;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest

class AuthServiceImplementationTest {

    @Autowired
    AuthServiceImplementation authServiceImplementation;

    @MockBean
    UserJpaRepository userJpaRepository;

    @MockBean
    RoleJpaRepository roleJpaRepository;

    @MockBean
    UserService userService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    RefreshTokenService refreshTokenService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    AuthenticationManager authenticationManager;

    @Test
    public void signupTest(){
        User mockUser = new User("User","123");
        Role role = new Role(RoleName.ROLE_USER);
        mockUser.setRoles(Set.of(role));
        AuthenticatedRequest registerRequest = new AuthenticatedRequest(mockUser.getName(), mockUser.getPassword());

        Mockito.when(userJpaRepository.save(Mockito.any())).thenReturn(mockUser);
        Mockito.when(roleJpaRepository.findByRoleName(RoleName.ROLE_USER)).thenReturn(role);
        Mockito.when(bCryptPasswordEncoder.encode(registerRequest.getPassword())).thenReturn("123");

        User signupUser = authServiceImplementation.signup(registerRequest);

        Mockito.verify(userJpaRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(roleJpaRepository, Mockito.times(1)).findByRoleName(RoleName.ROLE_USER);
        Mockito.verify(bCryptPasswordEncoder, Mockito.times(1)).encode(registerRequest.getPassword());

        Assertions.assertNotNull(signupUser);
        Assertions.assertEquals(RoleName.ROLE_USER, signupUser.getRoles().stream().findFirst().get().getRoleName());
        Assertions.assertEquals(bCryptPasswordEncoder.encode(registerRequest.getPassword()),signupUser.getPassword());
    }

    @Test
    public void loginTest(){
        AuthenticatedRequest registerRequest = new AuthenticatedRequest("User","123");
        User mockUser = new User("User", "123");
        mockUser.setRoles(Set.of(new Role(RoleName.ROLE_USER)));
        RefreshToken mockRefreshToken = new RefreshToken();
        mockRefreshToken.setToken("refreshToken");

        Mockito.when(userService.findUserByName(registerRequest.getUsername())).thenReturn(mockUser);
        Mockito.when(jwtUtil.createToken(mockUser.getName(), mockUser.getRoles())).thenReturn("token");
        Mockito.when(refreshTokenService.generateRefreshToken()).thenReturn(mockRefreshToken);
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(Mockito.any(Authentication.class));

        AuthenticatedResponse loginUser = authServiceImplementation.login(registerRequest);

        Mockito.verify(userService, Mockito.times(1)).findUserByName(Mockito.any());
        Mockito.verify(jwtUtil, Mockito.times(1)).createToken(Mockito.anyString(),Mockito.anySet());
        Mockito.verify(refreshTokenService, Mockito.times(1)).generateRefreshToken();
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(Mockito.any());

        Assertions.assertNotNull(loginUser);
        Assertions.assertEquals("token",loginUser.getAuthenticationToken());
        Assertions.assertEquals("refreshToken",loginUser.getRefreshToken());
    }
}