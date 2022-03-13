package com.alex.blog.service.impl;

import com.alex.blog.dao.mapper.SysUserMapper;
import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.service.SysUserService;
import com.alex.blog.service.TokenService;
import com.alex.blog.vo.ErrorCode;
import com.alex.blog.vo.LoginUserVo;
import com.alex.blog.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 19:27 - 周六
 **/


@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;


    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);

        // 防止为空
        if(sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("alex");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        // 用多少 查询多少
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    @Override
    public Result findUserByToken(String token) {
        /**
         * 1. token合法性校验
         *      是否为空，解析是否成功 redis是否存在
         *
         * 2. 如果校验失败，返回错误
         * 3. 如果成功，返回对应的结果 LoginUserVo
         */

        SysUser sysUser = tokenService.checkToken(token);
        if(sysUser == null){
            return Result.failure(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }

        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);
    }

    /**
     * 根据账户保存用户
     * @param account
     * @return
     */
    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 保存用户
     * @param sysUser
     */
    @Override
    public void save(SysUser sysUser) {

        // 保存用户时 id会自动生成
        // 默认生成的id是 分布式id 雪花算法
        sysUserMapper.insert(sysUser);

    }
}
