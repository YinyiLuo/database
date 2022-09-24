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
public class Open extends BaseEntity {
    private String user_id;

    private String album_id;

    private String index_last_played_track;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Open open = (Open) o;
        return user_id.equals(open.user_id) && album_id.equals(open.album_id) && index_last_played_track.equals(open.index_last_played_track);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user_id, album_id, index_last_played_track);
    }
}
