package com.dbdev.music.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;


@Entity
@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    private Long userId;

    private Long albumId;

    private String context;

    private String commentedTime;

//    private String name;//自己的名字
//    private String parentName;//父评论的名字

    private Long parentId;//父评论的id，

    private Long rootParentId;//最顶级的评论的id  形成二维

    @Transient
    private List<Comment> child; //本评论下的子评论

//    //对评论的评论的id
//    //假设我是id为5的评论，这个属性就是评论id为4的评论
//    //这样的话就是一个树了
//    private Long CommentOn;


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
