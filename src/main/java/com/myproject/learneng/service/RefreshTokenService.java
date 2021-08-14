package com.myproject.learneng.service;

import com.myproject.learneng.domain.RefreshToken;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken();
    void validateRefreshToken(String token);
    void deleteRefreshToken(String token);
}
