package com.alex.blog.dao.mapper;

import com.alex.blog.dao.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author Alexandermucc
 * @date 2022/3/12 - 17:49 - 周六
 **/
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id 查询标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询 limit的 最热标签
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(int limit);

    /**
     * 根据查询到的标签名称查询对应的标签名字
     * @param tagIds
     * @return
     */
    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
