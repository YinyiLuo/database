package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class MakeInfo {
    private String albumId;
    private String artistId;
    private String year;
}
