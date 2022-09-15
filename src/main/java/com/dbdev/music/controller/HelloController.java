package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private SysUserRepository sysUserRepository;

    @GetMapping("/helloWorld")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public AjaxResult getHelloWorld() {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", sysUserRepository.findByName("zhangsan").get());
        return ajaxResult;
    }
}
