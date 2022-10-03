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

    private String password;

    private String name;

    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SysUser sysUser = (SysUser) o;
        return password.equals(sysUser.password) && name.equals(sysUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password, name);
    }
}
