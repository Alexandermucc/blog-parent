package com.alex.blog.service;

import com.alex.blog.vo.Result;
import com.alex.blog.vo.params.PageParams;
import org.springframework.stereotype.Service;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 17:59 - 周六
 **/


public interface ArticleService {

    /**
     * 分页查询 文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticle(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

}
