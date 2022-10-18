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
    private String commentedTime;
}
