package com.dbdev.music.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.*;


@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Comment extends BaseEntity {
    private Long userId;
    private Long albumId;

    private String context;

    private Long parentId;//父评论的id，

    private Long rootParentId;//最顶级的评论的id  形成二维

    @ManyToOne(optional = false)
    @JoinColumn(name = "rootParentId", insertable=false, updatable=false)
    private Comment parent;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parent")
    private List<Comment> child; //本评论下的子评论

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(userId, comment.userId) && Objects.equals(albumId, comment.albumId) && Objects.equals(context, comment.context) && Objects.equals(parentId, comment.parentId) && Objects.equals(rootParentId, comment.rootParentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, albumId, context, parentId, rootParentId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", albumId=" + albumId +
                ", context='" + context + '\'' +
                ", parentId=" + parentId +
                ", rootParentId=" + rootParentId +
                ", child=" + child +
                '}';
    }
}
