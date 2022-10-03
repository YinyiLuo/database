package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class CollectInfo {
    private String userName;
    private String albumName;
    private String collectedTime;
}
