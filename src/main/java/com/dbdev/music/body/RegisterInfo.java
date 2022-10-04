package com.dbdev.music.body;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class RegisterInfo {
    private String email;
    private String code;
    private String password;
    private String username;
    private String mark;
}
