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
    private String track_id;

    private String album_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Belong_to belong_to = (Belong_to) o;
        return track_id.equals(belong_to.track_id) && album_id.equals(belong_to.album_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), track_id, album_id);
    }
}
