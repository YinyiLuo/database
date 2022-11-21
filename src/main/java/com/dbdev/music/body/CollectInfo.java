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
public class CollectInfo {
    private Long userId;
    private Long albumId;
    private Timestamp collectedTime;
}
