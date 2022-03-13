package com.alex.blog.service;

import com.alex.blog.vo.Result;
import com.alex.blog.vo.params.LoginParam;

/**
 * @author Alexandermucc
 * @date 2022/3/13 - 8:46 - 周日
 **/
public interface LoginService {
    /**
     * 登录
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
