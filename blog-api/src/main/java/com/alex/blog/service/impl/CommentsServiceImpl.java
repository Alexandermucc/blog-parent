package com.alex.blog.service.impl;

import com.alex.blog.dao.mapper.CommentMapper;
import com.alex.blog.dao.pojo.SysUser;
import com.alex.blog.service.CommentsService;
import com.alex.blog.service.SysUserService;
import com.alex.blog.utils.UserThreadLocal;
import com.alex.blog.vo.CommentVo;
import com.alex.blog.vo.Result;
import com.alex.blog.vo.UserVo;
import com.alex.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.alex.blog.dao.pojo.Comment;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandermucc
 * @date 2022/3/17 - 18:17 - 周四
 **/

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result commentsByArticleId(Long id) {
        /**
         * 1. 根据文章id 查询 评论列表 从 comment 表中查询
         * 2. 根据作者的id 查询作者的信息
         * 3. 判断 如果 level = 1 要去查询它有没有子评论
         * 4. 如果有 根据评论id 进行查询 （parent_id）
         */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //根据文章id进行查询
        queryWrapper.eq(Comment::getArticleId, id);
        //根据层级关系进行查询
        queryWrapper.eq(Comment::getLevel, 1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);

        List<CommentVo> commentVoList = copyList(comments);
        return Result.success(commentVoList);
    }


    @Override
    public Result comment(CommentParam commentParam) {
        //拿到当前用户
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        //如果是空，parent就是0
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insert(comment);
        return Result.success(null);
    }

    //对list表中的comment进行判断
    public List<CommentVo> copyList(List<Comment> commentList) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        // 相同属性copy
        BeanUtils.copyProperties(comment, commentVo);
        commentVo.setId(String.valueOf(comment.getId()));
        //作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVo = this.sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        //子评论
        Integer level = comment.getLevel();
        if (1 == level) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        //to User 给谁评论
        if (level > 1) {
            Long toUid = comment.getToUid();
            UserVo toUserVo = this.sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel, 2);
        List<Comment> comments = this.commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }

}
