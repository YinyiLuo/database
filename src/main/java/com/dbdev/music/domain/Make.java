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
public class Make extends BaseEntity {
    private String albumId;

    private String artistId;

    private String year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Make make = (Make) o;
        return albumId.equals(make.albumId) && artistId.equals(make.artistId) && year.equals(make.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), albumId, artistId, year);
    }
}
