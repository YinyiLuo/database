package com.dbdev.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long trackId;

    private String latestDateTimePlaybackBegan;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Listen listen = (Listen) o;
        return userId.equals(listen.userId) && trackId.equals(listen.trackId) && latestDateTimePlaybackBegan.equals(listen.latestDateTimePlaybackBegan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, trackId, latestDateTimePlaybackBegan);
    }
}
