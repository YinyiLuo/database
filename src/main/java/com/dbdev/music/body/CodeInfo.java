package com.dbdev.music.body;

import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CodeInfo {
    private String email;
    private String mark;
}
