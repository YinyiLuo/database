package com.dbdev.music.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class ListenInfo {
    private String userId;
    private String trackId;
    private String latestDateTimePlaybackBegan;
}
