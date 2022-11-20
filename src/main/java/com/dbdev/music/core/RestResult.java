package com.dbdev.music.core;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RestResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 72987893242353080L;

    private int code;
    private List<Map<String, String>> data;
    private String msg;
}
