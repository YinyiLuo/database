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
    private String userId;

    private String albumId;

    private String context;

    private String commentedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(userId, comment.userId) && Objects.equals(albumId, comment.albumId) && Objects.equals(context, comment.context) && Objects.equals(commentedTime, comment.commentedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, albumId, context, commentedTime);
    }
}
