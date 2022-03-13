package com.alex.blog.service.impl;

import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.service.LoginService;
import com.alex.blog.service.SysUserService;
import com.alex.blog.utils.JWTUtils;
import com.alex.blog.vo.ErrorCode;
import com.alex.blog.vo.Result;
import com.alex.blog.vo.params.LoginParam;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Alexandermucc
 * @date 2022/3/13 - 8:52 - 周日
 **/

@Service
public class LoginServiceImpl implements LoginService {


    // 加密盐
    private static final String slat = "mszlu!@#";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录功能实现
     * @param loginParam
     * @return
     */
    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1. 检查参数是否合法
         * 2. 根据用户名和密码去user表中查询 是否存在
         * 3. 不存在则登录失败
         * 4. 如果存在使用jwt,生成token,返回给前端
         * 5. token放入到redis中,redis token：user信息 设置过期时间
         *      (登录认证时候, 先认证字符串是否合法,去redis验证,是否存在)
         */

        String account = loginParam.getAccount();
        String password = loginParam.getPassword();

        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.failure(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        // password 加密
        password = DigestUtils.md5Hex(password + slat);

        SysUser sysUser = sysUserService.findUser(account, password);

        if (sysUser == null){
            return Result.failure(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }


        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);

        return Result.success(token);
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }
}
