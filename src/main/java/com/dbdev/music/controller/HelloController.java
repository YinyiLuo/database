package com.dbdev.music.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/helloWorld")
    public String getHelloWorld() {
        return "Hello world";
    }
}
