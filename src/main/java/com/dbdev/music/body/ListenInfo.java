package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class ListenInfo {
    private Long userId;
    private Long trackId;
    private String latestDateTimePlaybackBegan;
}
