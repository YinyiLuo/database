package com.dbdev.music.body;

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
    private String latestDateTimePlayBackBegan;
}
