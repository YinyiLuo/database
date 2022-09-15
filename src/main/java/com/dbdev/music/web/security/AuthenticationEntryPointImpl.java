package com.dbdev.music.web.security;

import com.dbdev.music.constant.HttpStatus;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.utils.ServletUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Serial
    private static final long serialVersionUID = -8970710437077606L;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String msg = String.format("请求访问: %s, 认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response,
                objectMapper.writeValueAsString(AjaxResult.error(HttpStatus.UNAUTHORIZED, msg)));
    }
}
