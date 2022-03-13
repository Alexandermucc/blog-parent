package com.alex.blog.service;

import com.alex.blog.vo.Result;
import com.alex.blog.vo.TagVo;

import java.util.List;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 18:50 - 周六
 **/
public interface TagService {

    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 查询最热门的文章
     * @param limit
     * @return
     */
    Result hots(int limit);
}
