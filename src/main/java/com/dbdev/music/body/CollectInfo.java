package com.dbdev.music.body;

import lombok.*;

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
    private String collectedTime;
}
