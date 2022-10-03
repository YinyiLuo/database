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
public class Belong_to extends BaseEntity {
    private String trackName;

    private String albumName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Belong_to belong_to = (Belong_to) o;
        return trackName.equals(belong_to.trackName) && albumName.equals(belong_to.albumName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trackName, albumName);
    }
}
