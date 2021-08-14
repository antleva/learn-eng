package com.myproject.learneng.service.impl;

import com.myproject.learneng.dao.RefreshTokenRepository;
import com.myproject.learneng.domain.RefreshToken;
import com.myproject.learneng.exception.CustomErrorType;
import com.myproject.learneng.service.RefreshTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class RefreshTokenServiceImplementation implements RefreshTokenService {
    private static final Logger logger = Logger.getLogger(RefreshTokenServiceImplementation.class);
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenServiceImplementation(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken generateRefreshToken() {
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setToken(UUID.randomUUID().toString());
            logger.info("create refresh token");
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validateRefreshToken(String token) {
            logger.info("validate refresh token");
            refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new CustomErrorType("Invalid refresh Token"));
    }

    @Override
    public void deleteRefreshToken(String token) {
            logger.info("delete refresh token");
            refreshTokenRepository.deleteById(refreshTokenRepository.
                findByToken(token).orElseThrow(() -> new CustomErrorType("Empty refresh Token")).getId());
    }
}
