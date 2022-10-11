package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class ListenInfo {
    private Long userId;
    private Long trackId;
    private String latestDateTimePlaybackBegan;
}
