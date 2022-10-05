package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.SysUser;
import com.dbdev.music.body.SysUserInfo;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysUserController {

    @Autowired
    private SysUserRepository sysUserRepository;

    @GetMapping("/sysuser/getAllUser")
    public AjaxResult getAllUser() {
        return AjaxResult.success(sysUserRepository.findAll());
    }

    @GetMapping("/sysuser/findUserByNameLike/{name}/{page}/{size}")
    public AjaxResult findUserByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findUserByNameLike");
        Page<SysUser> byName = sysUserRepository.findSysUserByNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/sysuser/addUser")
    public AjaxResult addUser(@RequestBody SysUserInfo info) {
        sysUserRepository.save(
                SysUser.builder()
                        .email(info.getEmail())
                        .name(info.getName())
                        .password(info.getPassword())
                        .role(info.getRole())
                        .build()
        );
        return AjaxResult.success();
    }
}
