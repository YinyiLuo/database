package com.dbdev.music.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class MakeInfo {
    private String album_id;
    private String artist_id;
    private String year;
}
