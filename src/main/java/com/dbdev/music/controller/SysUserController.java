package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.SysUser;
import com.dbdev.music.body.SysUserInfo;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysUserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserRepository sysUserRepository;

    @GetMapping("/sysuser/getAllUser/{page}/{size}")
    public AjaxResult getAllUser(@PathVariable("page") int page, @PathVariable("size") int size) {
        return AjaxResult.success(sysUserRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("sysuser/findById/{id}")
    public AjaxResult findById(@PathVariable("id") Long id) {
        System.out.println("findUserById");
        var byId = sysUserRepository.findById(id);
        return AjaxResult.success(byId);
    }

    @GetMapping("/sysuser/findUsersByNameLike/{name}/{page}/{size}")
    public AjaxResult findUsersByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findUsersByNameLike");
        var byName = sysUserRepository.findByNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

//    @PostMapping("/sysuser/addUser")
//    public AjaxResult addUser(@RequestBody SysUserInfo info) {
//        sysUserRepository.save(
//                SysUser.builder()
//                        .email(info.getEmail())
//                        .name(info.getName())
//                        .password(passwordEncoder.encode(info.getPassword()))
//                        .role("ROLE_STUDENT")
//                        .build()
//        );
//        return AjaxResult.success();
//    }
}
