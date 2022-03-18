package com.alex.blog.service;

import com.alex.blog.vo.CategoryVo;
import com.alex.blog.vo.Result;


public interface CategoryService {

    CategoryVo findCategoryById(Long id);

    Result findAll();

    Result findAllDetail();
}

