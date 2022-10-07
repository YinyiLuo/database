package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class CommentInfo {
    private String userId;
    private String albumId;
    private String context;
    private String commentedTime;
}
