package com.sxx.aclservice.controller;


import com.sxx.aclservice.entity.Permission;
import com.sxx.aclservice.service.PermissionService;
import com.sxx.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 权限 菜单管理
 * @author SxxStar
 * @date 2023.8.1
 */
@Api(description = "菜单管理")
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * @description 获取全部菜单
     * @return
     */
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public R indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return R.ok().data("children",list);
    }

    /**
     * @description 递归删除菜单
     * @param id
     * @return
     */
    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable @ApiParam(name="id",value = "菜单ID") String id) {
        permissionService.removeChildById(id);
        return R.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public R doAssign(@RequestParam(name = "roleId") @ApiParam(name = "roleId",value = "角色ID") String roleId,@RequestParam(name = "permissionId") @ApiParam(name = "permissionId",value = "菜单ID") String[] permissionId) {
        permissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return R.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public R toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return R.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public R save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return R.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public R updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return R.ok();
    }

}

