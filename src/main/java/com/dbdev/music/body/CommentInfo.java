package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class CommentInfo {
    private String userName;
    private String albumName;
    private String context;
    private String commentedTime;
}
