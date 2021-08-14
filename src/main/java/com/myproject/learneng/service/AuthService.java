package com.myproject.learneng.service;

import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.AuthenticatedRequest;
import com.myproject.learneng.dto.AuthenticatedResponse;
import com.myproject.learneng.dto.RefreshTokenRequest;

public interface AuthService {

    User signup(AuthenticatedRequest registerRequest);

    AuthenticatedResponse login(AuthenticatedRequest loginRequest);

    User getCurrentUser();

    AuthenticatedResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
