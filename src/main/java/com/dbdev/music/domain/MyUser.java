package com.dbdev.music.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MyUser extends BaseEntity {
    private String name;
    private String account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyUser myUser = (MyUser) o;
        return Objects.equals(name, myUser.name) && Objects.equals(account, myUser.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, account);
    }
}
