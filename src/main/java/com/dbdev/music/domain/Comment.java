package com.dbdev.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.util.*;


@Entity
//@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Comment extends BaseEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long albumId;

    private String context;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;//父评论的id，

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long rootParentId;//最顶级的评论的id  形成二维

    private int likeCnt;    // 点赞数目

    static public Sort sort = Sort.by(Sort.Direction.ASC, "createTime");

    @ManyToOne(optional = false)
    @JoinColumn(name = "parentId", insertable = false, updatable = false)
    @JsonIgnore
    private Comment parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Comment> child; //本评论下的子评论

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(userId, comment.userId) && Objects.equals(albumId, comment.albumId) && Objects.equals(context, comment.context) && Objects.equals(parentId, comment.parentId) && Objects.equals(rootParentId, comment.rootParentId) && Objects.equals(likeCnt, comment.likeCnt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, albumId, context, parentId, rootParentId, likeCnt);
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
                ", likeCnt=" + likeCnt +
                '}';
    }
}
