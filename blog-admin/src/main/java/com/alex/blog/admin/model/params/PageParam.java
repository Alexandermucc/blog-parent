package com.alex.blog.admin.model.params;

import lombok.Data;

/**
 * @author Alexandermucc
 * @date 2022/3/22 - 21:32 - 周二
 **/

@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
