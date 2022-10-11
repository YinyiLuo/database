package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class OpenInfo {
    private Long userId;
    private Long albumId;
    private int indexLastPlayedTrack;
}
