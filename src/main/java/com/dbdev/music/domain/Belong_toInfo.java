package com.dbdev.music.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class Belong_toInfo {
    private String track_id;
    private String album_id;
}
