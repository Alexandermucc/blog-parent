package com.alex.blog.controller;

import com.alex.blog.service.SysUserService;
import com.alex.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexandermucc
 * @date 2022/3/13 - 9:52 - 周日
 **/
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;


    //  /users/currentUser
    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return sysUserService.findUserByToken(token);
    }


}
