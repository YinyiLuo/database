package com.dbdev.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.*;


@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentLike extends BaseEntity{
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long commentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CommentLike like = (CommentLike) o;
        return Objects.equals(userId, like.userId) && Objects.equals(commentId, like.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, commentId);
    }
}
