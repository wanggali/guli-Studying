package com.guli.utils.exceptionhandler;


import com.guli.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //指定异常执行方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了能够返回数据
    public Result error(Exception e){
        log.error("Default Exception: "+e.getMessage());
        return Result.error().message("方法执行ArithmeticException异常！");
    }

    //自定义的异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e){
        log.error("GuliException Exception: "+e.getMessage());
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

}
