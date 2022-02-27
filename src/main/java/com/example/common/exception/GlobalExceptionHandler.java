package com.example.common.exception;


import com.example.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.ShiroException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RestControllerAdvice都是对Controller进行增强的，可以全局捕获spring mvc抛的异常。
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e, HttpServletResponse response){
        log.error("运行时异常：--------{}",e);
        return Result.fail(String.valueOf(response.getStatus()), e.getMessage(), null);
    }

    /**
     * 处理Assert的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        log.error("Assert异常:-------------->{}",e.getMessage());
        return Result.fail(String.valueOf(response.getStatus()), e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e, HttpServletResponse response){
        log.error("实体检验异常：--------{}",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(String.valueOf(response.getStatus()), objectError.getDefaultMessage());
    }


    //改变http响应的状态码
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e, HttpServletResponse response){
        log.error("运行时异常：--------{}",e);
        return Result.fail(String.valueOf(response.getStatus()), e.getMessage());
    }

}
