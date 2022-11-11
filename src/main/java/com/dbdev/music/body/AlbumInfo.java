package com.dbdev.music.body;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class AlbumInfo {
    private String name;
}
