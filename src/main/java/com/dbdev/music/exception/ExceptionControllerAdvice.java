package com.dbdev.music.exception;

import com.dbdev.music.core.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
//    @ExceptionHandler(Exception.class)
//    public AjaxResult notFound(Exception exception) {
//        System.out.println(exception.toString());
//        return AjaxResult.success();
//    }
}
