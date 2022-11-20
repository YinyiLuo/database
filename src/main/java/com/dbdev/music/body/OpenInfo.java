package com.dbdev.music.body;

import lombok.*;

import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class OpenInfo {
    private Long userId;
    private Long albumId;
    private Timestamp latestDateTimePlaybackBegan;
}
