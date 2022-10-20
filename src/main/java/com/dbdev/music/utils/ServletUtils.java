package com.dbdev.music.utils;

import com.dbdev.music.constant.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(HttpStatus.SUCCESS);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
