package com.dbdev.music.web.security;

import com.dbdev.music.core.model.LoginUser;
import com.dbdev.music.domain.SysUser;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SysUser> sysUser = sysUserRepository.findByName(username);
        if(sysUser.isEmpty()) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return new LoginUser(sysUser.get());
    }
}
