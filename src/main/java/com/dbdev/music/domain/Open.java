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
    private String userName;

    private String albumName;

    private int indexLastPlayedTrack;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Open open = (Open) o;
        return userName.equals(open.userName) && albumName.equals(open.albumName) && indexLastPlayedTrack == open.indexLastPlayedTrack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, albumName, indexLastPlayedTrack);
    }
}
