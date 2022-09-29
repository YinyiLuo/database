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
public class Listen extends BaseEntity {
    private String userId;

    private String trackId;

    private String latestDateTimePlayBackBegan;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Listen listen = (Listen) o;
        return userId.equals(listen.userId) && trackId.equals(listen.trackId) && latestDateTimePlayBackBegan.equals(listen.latestDateTimePlayBackBegan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, trackId, latestDateTimePlayBackBegan);
    }
}
