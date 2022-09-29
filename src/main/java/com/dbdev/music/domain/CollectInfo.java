package com.dbdev.music.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class CollectInfo {
    private String userId;
    private String albumId;
    private String collectedTime;
}
