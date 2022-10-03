package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.core.model.LoginBody;
import com.dbdev.music.service.SysLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLoginController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        String token = sysLoginService.login(loginBody.getEmail(), loginBody.getPassword());
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("token", token);
        return ajaxResult;
    }
}
