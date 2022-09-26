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
public class Comment extends BaseEntity {
    private String user_id;

    private String album_id;

    private String context;

    private String commented_time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return user_id.equals(comment.user_id) && album_id.equals(comment.album_id) && context.equals(comment.context) && commented_time.equals(comment.commented_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user_id, album_id, commented_time);
    }
}
