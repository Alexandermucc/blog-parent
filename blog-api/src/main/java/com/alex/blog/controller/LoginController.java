package com.alex.blog.controller;

import com.alex.blog.service.LoginService;
import com.alex.blog.vo.Result;
import com.alex.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    // 为什么不直接使用SysUserService？
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){

        // 登录 验证用户 访问验证表
        return loginService.login(loginParam);
    }
}
