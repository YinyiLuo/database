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
public class Collect extends BaseEntity {
    private String user_id;

    private String album_id;

    private String collected_time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Collect collect = (Collect) o;
        return user_id.equals(collect.user_id) && album_id.equals(collect.album_id) && collected_time.equals(collect.collected_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user_id, album_id, collected_time);
    }
}
