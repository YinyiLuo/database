package com.dbdev.music.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class TrackInfo {
    private String name;
    private String time_length;
}
