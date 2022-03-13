package com.alex.blog.service;

import com.alex.blog.dao.pojo.SysUser;

/**
 * @author Alexandermucc
 * @date 2022/3/13 - 10:34 - 周日
 **/
public interface TokenService {

    // 根据Token 查询用户信息
    SysUser checkToken(String token);
}
