package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class CommentInfo {
    private Long userId;
    private Long albumId;
    private String context;
    private Long parentId;//父评论的id，
    private Long rootParentId;//最顶级的评论的id  形成二维
}
