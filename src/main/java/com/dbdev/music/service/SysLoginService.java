package com.dbdev.music.service;

import com.dbdev.music.core.model.LoginUser;
import com.dbdev.music.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String login(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            return tokenService.createToken(loginUser);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
