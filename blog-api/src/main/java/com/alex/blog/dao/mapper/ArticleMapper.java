package com.alex.blog.dao.mapper;

import com.alex.blog.dao.dos.Archives;
import com.alex.blog.dao.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 17:47 - 周六
 **/
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 返回归档 数据
     * @return
     */
    List<Archives> listArchives();
}
