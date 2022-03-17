package com.alex.blog.service;

import com.alex.blog.vo.Result;
import com.alex.blog.vo.params.CommentParam;

public interface CommentsService {

    /**
     * 根据文章id查询所有的评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    /**
     * 评论功能
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}

