package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class CommentLikeInfo {
    private Long userId;
    private Long commentId;
}
