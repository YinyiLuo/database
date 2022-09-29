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
    private String trackId;

    private String albumId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Belong_to belong_to = (Belong_to) o;
        return trackId.equals(belong_to.trackId) && albumId.equals(belong_to.albumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trackId, albumId);
    }
}
