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
public class Collect extends BaseEntity {
    private String userName;

    private String albumName;

    private String collectedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Collect collect = (Collect) o;
        return userName.equals(collect.userName) && albumName.equals(collect.albumName) && collectedTime.equals(collect.collectedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, albumName, collectedTime);
    }
}
