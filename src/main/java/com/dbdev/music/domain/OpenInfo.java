package com.dbdev.music.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class OpenInfo {
    private String user_id;
    private String album_id;
    private String index_last_played_track;
}
