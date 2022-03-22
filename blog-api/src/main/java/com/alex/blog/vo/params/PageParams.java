package com.alex.blog.vo.params;

import lombok.Data;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 17:52 - 周六
 **/
@Data
public class PageParams {

    private int page = 1;

    private int pageSize =10;

    private Long categoryId;

    private Long tagId;
}
