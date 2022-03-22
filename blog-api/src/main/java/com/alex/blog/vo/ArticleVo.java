package com.alex.blog.vo;


import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.dao.pojo.Tag;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

//    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private List<TagVo> tags;

    private CategoryVo category;

    private ArticleBodyVo body;
}

