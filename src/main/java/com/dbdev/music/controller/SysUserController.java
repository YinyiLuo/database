package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.core.model.LoginUser;
import com.dbdev.music.core.redis.RedisCache;
import com.dbdev.music.domain.SysUser;
import com.dbdev.music.body.SysUserInfo;
import com.dbdev.music.repository.SysUserRepository;
import com.dbdev.music.service.SysLoginService;
import com.dbdev.music.service.SysUserService;
import com.dbdev.music.service.TokenService;
import lombok.Data;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class SysUserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLoginService sysLoginService;

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

    @GetMapping("/sysuser/findCurrentUser")
    public AjaxResult findCurrentUser(HttpServletRequest request) {
        return AjaxResult.success(tokenService.getLoginUser(request).getSysUser());
    }

    @Data
    static class ModifyInfo {
        private String name;
        private String email;
    }

    @Data
    static class ModifyInfo_ {
        private String password;
    }


    @PutMapping("/sysuser/modifyNameAndEmail")
    @Transactional
    public AjaxResult modifyNameAndEmail(@RequestBody ModifyInfo modifyInfo, HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        sysUserRepository.updateNameAndEmail(loginUser.getSysUser().getId(),
                modifyInfo.name, modifyInfo.email);
        loginUser.getSysUser().setName(modifyInfo.getName());
        loginUser.getSysUser().setEmail(modifyInfo.getEmail());
        tokenService.refreshToken(loginUser);
        return AjaxResult.success("信息修改成功");
    }

    @PutMapping("/sysuser/modifyPassword")
    @Transactional
    public AjaxResult modifyPassword(@RequestBody ModifyInfo_ modifyInfo_, HttpServletRequest request) {
        System.out.println("1");
        LoginUser loginUser = tokenService.getLoginUser(request);
        System.out.println("2");
        var encoded = passwordEncoder.encode(modifyInfo_.password);
        sysUserRepository.updatePassword(loginUser.getSysUser().getId(), encoded);
        loginUser.getSysUser().setPassword(encoded);
        tokenService.refreshToken(loginUser);
        return AjaxResult.success("密码修改成功");
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
