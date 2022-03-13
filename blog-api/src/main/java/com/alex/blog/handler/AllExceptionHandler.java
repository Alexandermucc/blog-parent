package com.alex.blog.handler;

import com.alex.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 20:31 - 周六
 **/
// 加了@Controller 注解的方法进行拦截处理AOP的实现
@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.failure(-999,"系统异常");
    }

}
