package com.dbdev.music.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
@Getter
@Setter
@Slf4j
// To be updated
public class SysUser extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 427891784792L;

    private String email;

    private String name;

    private String password;

    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(password, sysUser.password) && Objects.equals(email, sysUser.email) && Objects.equals(name, sysUser.name) && Objects.equals(role, sysUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password, email, name, role);
    }
}
