package com.alex.blog.service;

import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.vo.Result;
import com.alex.blog.vo.UserVo;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 19:26 - 周六
 **/
public interface SysUserService {


    // 根据userId查询用户
    SysUser findUserById(Long id);

    // 查询用户信息
    SysUser findUser(String account, String password);

    // 根据token查询用户信息
    Result findUserByToken(String token);

    // 根据账户查找用户
    SysUser findUserByAccount(String account);

    // 保存用户
    void save(SysUser sysUser);

    // 通过id查询UserVo
    UserVo findUserVoById(Long authorId);
}
