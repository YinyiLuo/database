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
    private String userName;

    private String albumName;

    private String context;

    private String commentedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(userName, comment.userName) && Objects.equals(albumName, comment.albumName) && Objects.equals(context, comment.context) && Objects.equals(commentedTime, comment.commentedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, albumName, context, commentedTime);
    }
}
