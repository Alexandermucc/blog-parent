package com.alex.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Alexandermucc
 * @date 2022/3/22 - 21:39 - 周二
 **/
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;

}
