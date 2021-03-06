package com.alex.blog.dao.mapper;

import com.alex.blog.dao.dos.Archives;
import com.alex.blog.dao.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 17:47 - 周六
 **/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 返回归档 数据
     * @return
     */
    List<Archives> listArchives();

    IPage<Article> listArticle(Page<Article> page, Long categoryId, Long tagId, String year, String month);
}
