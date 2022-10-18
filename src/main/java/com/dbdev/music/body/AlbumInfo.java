package com.dbdev.music.body;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class AlbumInfo {
    private String name;
}
