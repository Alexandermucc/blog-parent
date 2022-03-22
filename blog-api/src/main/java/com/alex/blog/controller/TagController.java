package com.alex.blog.controller;

import com.alex.blog.service.TagService;
import com.alex.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 19:53 - 周六
 **/

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // tags/hot
    @GetMapping("hot")
    public Result hot(){
        int limit = 6;
        return tagService.hots(limit);
    }

    @GetMapping
    public Result findAll(){
        /**
         * 查询所有的文章标签
         * @return
         */
        return tagService.findAll();
    }

    @GetMapping("detail")
    public Result findAllDetail(){
        /**
         * 查询所有的文章标签
         * @return
         */
        return tagService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public Result findDetailById(@PathVariable("id") Long id){
        return tagService.findDetailById(id);
    }


}
