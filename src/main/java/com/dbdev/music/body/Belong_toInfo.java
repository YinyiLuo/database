package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class Belong_toInfo {
    private String trackName;
    private String albumName;
}
