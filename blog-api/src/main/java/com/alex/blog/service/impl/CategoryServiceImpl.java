package com.alex.blog.service.impl;

import com.alex.blog.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.alex.blog.dao.mapper.CategoryMapper;
import com.alex.blog.dao.pojo.Category;
import com.alex.blog.service.CategoryService;
import com.alex.blog.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//注入spring 
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Long id){
        Category category = categoryMapper.selectById(id);
        CategoryVo categoryVo = new CategoryVo();
        //因为category,categoryVo属性一样所以可以使用 BeanUtils.copyProperties
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId,Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        // 没有任何参数，所有一个空的LambdaQueryWrapper即可
//        List<Category> categories = this.categoryMapper.selectList(new LambdaQueryWrapper<>());
        //页面交互的对象
        return Result.success(copyList(categories));
    }


    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        //页面交互的对象
        return Result.success(copyList(categories));
    }

    @Override
    public Result categoryDetailById(Long id) {
        Category category = categoryMapper.selectById(id);
        return Result.success(copy(category));
    }


    //id不一致要重新设立
    public CategoryVo copy(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        //id不一致要重新设立
//        categoryVo.setId(String.valueOf(category.getId()));
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }
    public List<CategoryVo> copyList(List<Category> categoryList){
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }



}

