package com.dbdev.music.domain;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class MyUserInfo {
    private String account;
    private String name;
}
