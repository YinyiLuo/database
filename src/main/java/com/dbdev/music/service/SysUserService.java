package com.dbdev.music.service;

import com.dbdev.music.domain.SysUser;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insertUser(String username, String password,
                           String email, String role) {
        sysUserRepository.save(
                SysUser.builder()
                        .name(username)
                        .password(passwordEncoder.encode(password))
                        .email(email)
                        .role(role)
                        .build()
        );
    }
}
