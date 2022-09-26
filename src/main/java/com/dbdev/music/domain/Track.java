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
public class Track extends BaseEntity {
    private String name;

    private String time_length;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Track track = (Track) o;
        return name.equals(track.name) && time_length.equals(track.time_length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, time_length);
    }
}
