package com.alex.blog.controller;

import com.alex.blog.service.CommentsService;
import com.alex.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexandermucc
 * @date 2022/3/17 - 18:17 - 周四
 **/

@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long articleId){
        return commentsService.commentsByArticleId(articleId);
    }


    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentsService.comment(commentParam);
    }


}
