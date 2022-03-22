package com.alex.blog.admin.controller;

import com.alex.blog.admin.model.params.PageParam;
import com.alex.blog.admin.pojo.Permission;
import com.alex.blog.admin.service.PermissionService;
import com.alex.blog.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexandermucc
 * @date 2022/3/22 - 21:29 - 周二
 **/

@RestController
@RequestMapping("admin")
public class adminController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping("permission/permissionList")
    public Result listPermissions(@RequestBody PageParam pageParam) {
        return permissionService.listPermissions(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }


}
