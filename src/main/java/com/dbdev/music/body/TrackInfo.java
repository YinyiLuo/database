package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class TrackInfo {
    private String name;
    private String timeLength;
    private String role;//管理员还是普通用户的
}
