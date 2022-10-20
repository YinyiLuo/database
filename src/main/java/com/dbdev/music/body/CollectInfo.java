package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class CollectInfo {
    private Long userId;
    private Long albumId;
    private String collectedTime;
}
