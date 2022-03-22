package com.alex.blog.dao.mapper;

import com.alex.blog.dao.pojo.Comment;
import com.alex.blog.service.CommentsService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Alexandermucc
 * @date 2022/3/17 - 18:34 - 周四
 **/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
