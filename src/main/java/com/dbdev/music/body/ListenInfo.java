package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class ListenInfo {
    private String userName;
    private String trackName;
    private String latestDateTimePlayBackBegan;
}
