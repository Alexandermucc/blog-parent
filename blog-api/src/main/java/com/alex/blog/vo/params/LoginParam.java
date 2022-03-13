package com.alex.blog.vo.params;

import lombok.Data;

/**
 * @author Alexandermucc
 * @date 2022/3/13 - 8:50 - 周日
 **/

// 用于登录功能的参数获取
@Data
public class LoginParam {

    private String account;

    private String password;

    private String nickname;
}
