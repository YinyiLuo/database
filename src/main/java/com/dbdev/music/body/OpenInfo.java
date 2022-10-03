package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class OpenInfo {
    private String userName;
    private String albumName;
    private int indexLastPlayedTrack;
}
