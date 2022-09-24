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
    private String album_id;

    private String artist_id;

    private String year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Make make = (Make) o;
        return album_id.equals(make.album_id) && artist_id.equals(make.artist_id) && year.equals(make.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), album_id, artist_id, year);
    }
}
