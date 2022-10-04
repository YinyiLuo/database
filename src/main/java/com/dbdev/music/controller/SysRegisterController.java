package com.dbdev.music.controller;

import com.dbdev.music.body.CodeInfo;
import com.dbdev.music.body.RegisterInfo;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.core.redis.RedisCache;
import com.dbdev.music.service.SysRegisterService;
import com.dbdev.music.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysRegisterController {
    @Autowired
    private SysRegisterService sysRegisterService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/code")
    public AjaxResult getCode(@RequestBody CodeInfo codeInfo) {
        if(codeInfo.getEmail() == null || !codeInfo.getEmail().matches(".*@.*\\.com")
        || codeInfo.getMark() == null || codeInfo.getMark().equals("")) {
            return AjaxResult.error();
        }
        String code = sysRegisterService.getCode(codeInfo.getEmail(), codeInfo.getMark());
        return AjaxResult.success(code);
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterInfo registerInfo) {
        String key = registerInfo.getEmail() + ":" + registerInfo.getMark();
        String code = (String) redisCache.getCacheObject(key);
        if(!registerInfo.getCode().equals(code)) {
            return AjaxResult.error();
        }
        redisCache.deleteObject(key);
        sysUserService.insertUser(
                registerInfo.getUsername(),
                registerInfo.getPassword(),
                registerInfo.getEmail(),
                "ROLE_USER"
        );
        return AjaxResult.success();
    }
}
