package com.myproject.learneng.controllers;

import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.AuthenticatedRequest;
import com.myproject.learneng.dto.AuthenticatedResponse;
import com.myproject.learneng.dto.RefreshTokenRequest;
import com.myproject.learneng.service.AuthService;
import com.myproject.learneng.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthService authService,RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signup(@RequestBody AuthenticatedRequest authenticatedUserDto) {
            User user = authService.signup(authenticatedUserDto);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @PostMapping("/login")
    public AuthenticatedResponse login(@RequestBody AuthenticatedRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticatedResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
            String token = refreshTokenRequest.getToken();
            refreshTokenService.deleteRefreshToken(token);
        return new ResponseEntity<>("Refresh Token Deleted Successfully!!",OK);

    }


}