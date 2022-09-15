package com.dbdev.music.core.model;

import com.dbdev.music.domain.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 114241241321L;

    @Setter @Getter private SysUser sysUser;
    @Setter @Getter private String token;
    @Setter @Getter private Long expireTime;
    @Setter @Getter private Long loginTime;

    public LoginUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> permission = new ArrayList<>();
        permission.add(new SimpleGrantedAuthority(sysUser.getRole()));
        return permission;
    }

    @Override @JsonIgnore
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override @JsonIgnore
    public String getUsername() {
        return sysUser.getName();
    }

    @Override @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
