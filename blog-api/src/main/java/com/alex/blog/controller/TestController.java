package com.alex.blog.controller;

import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.utils.UserThreadLocal;
import com.alex.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}

