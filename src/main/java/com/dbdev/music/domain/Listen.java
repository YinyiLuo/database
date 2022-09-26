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
    private String user_id;

    private String track_id;

    private String latest_date_time_playback_began;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Listen listen = (Listen) o;
        return user_id.equals(listen.user_id) && track_id.equals(listen.track_id) && latest_date_time_playback_began.equals(listen.latest_date_time_playback_began);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user_id, track_id, latest_date_time_playback_began);
    }
}
