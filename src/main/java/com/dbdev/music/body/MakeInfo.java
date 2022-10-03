package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class MakeInfo {
    private String albumName;
    private String artistName;
    private String year;
}
