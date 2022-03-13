package com.alex.blog.service.impl;

import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.service.TokenService;
import com.alex.blog.utils.JWTUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Alexandermucc
 * @date 2022/3/13 - 10:35 - 周日
 **/

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisTemplate<String,String> redisTemplate = new RedisTemplate<String,String>();

    /**
     * 根据token查询用户信息
     *
     * @param token
     * @return
     */
    @Override
    public SysUser checkToken(String token) {

        /**
         * 1. token合法性校验
         *      是否为空，解析是否成功 redis是否存在
         *
         * 2. 如果校验失败，返回错误
         * 3. 如果成功，返回对应的结果 LoginUserVo
         */

        if (StringUtils.isBlank(token)) {

            return null;
        }


        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        // 解析失败
        if (stringObjectMap == null){
            return null;
        }

        // 过期
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }

        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);

        return sysUser;
    }
}
