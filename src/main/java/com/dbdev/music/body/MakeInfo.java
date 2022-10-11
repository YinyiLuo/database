package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class MakeInfo {
    private Long albumId;
    private Long artistId;
    private String year;
}
