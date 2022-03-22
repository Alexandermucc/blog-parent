package com.alex.blog.service.impl;

import com.alex.blog.dao.mapper.TagMapper;
import com.alex.blog.dao.pojo.Tag;
import com.alex.blog.service.TagService;
import com.alex.blog.vo.Result;
import com.alex.blog.vo.TagVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 18:52 - 周六
 **/

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * ms_article_tag是文章和标签的关联表
     * ms_tag为单纯的标签的表
     * @param articleId
     * @return
     */
    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //mybatisplus无法进行多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    // 最热标签
    @Override
    public Result hots(int limit) {
        /**
         * 1. 标签所拥有的的文章数量最多 最热标签
         * 2. 查询 根据tag_id 分组 计数 从大到小 排序 取前limit个
         */


        List<Long> tagIds = tagMapper.findHotsTagIds(limit);

        if(CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }

        // 需要查询 Tag对象  tagIds 与 tagName
        List<Tag> tagList = tagMapper.findTagsByTagIds(tagIds);
        return Result.success(tagList);
    }


    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        // 用多少 查询多少
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }


    private List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;

    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }

}
