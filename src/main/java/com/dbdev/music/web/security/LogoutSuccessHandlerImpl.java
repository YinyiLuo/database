package com.dbdev.music.web.security;

import com.dbdev.music.constant.HttpStatus;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.core.model.LoginUser;
import com.dbdev.music.service.TokenService;
import com.dbdev.music.utils.ServletUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if(loginUser != null) {
            String userName = loginUser.getUsername();
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response,
                objectMapper.writeValueAsString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
