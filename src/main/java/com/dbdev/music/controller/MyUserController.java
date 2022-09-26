package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.MyUser;
import com.dbdev.music.domain.MyUserInfo;
import com.dbdev.music.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyUserController {

    @Autowired
    private MyUserRepository myUserRepository;

    @GetMapping("/myuser/getAllUser")
    public AjaxResult getAllUser() {
        return AjaxResult.success(myUserRepository.findAll());
    }

    @PostMapping("/myuser/addUser")
    public AjaxResult addUser(@RequestBody MyUserInfo info) {
        myUserRepository.save(
                MyUser.builder()
                        .account(info.getAccount())
                        .name(info.getName())
                        .build()
        );
        return AjaxResult.success();
    }
}
