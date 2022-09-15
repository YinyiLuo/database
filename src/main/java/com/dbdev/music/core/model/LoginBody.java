package com.dbdev.music.core.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class LoginBody {
    private String username;
    private String password;
}
