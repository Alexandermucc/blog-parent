package com.alex.blog.vo;


import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.dao.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private List<TagVo> tags;


}

