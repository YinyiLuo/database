package com.dbdev.music.body;

import lombok.*;

@ToString
@AllArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
public class SysUserInfo {
    private String email;
    private String name;
    private String password;
    private String role;
}
