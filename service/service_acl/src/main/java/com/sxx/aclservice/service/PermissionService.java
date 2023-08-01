package com.sxx.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.sxx.aclservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @description 权限
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionService extends IService<Permission> {

    /**
     * @description 获取全部菜单
     * @return
     */
    List<Permission> queryAllMenu();

    /**
     * @description 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<Permission> selectAllMenu(String roleId);

    /**
     * @description 给角色分配权限
     * @return
     */
    void saveRolePermissionRealtionShip(String roleId, String[] permissionIds);

    /**
     * @description 递归删除菜单
     * @return
     */
    void removeChildById(String id);

    //
    /**
     * @description 根据用户id获取用户菜单
     * @return
     */
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

    /**
     * @description 递归删除菜单
     * @return
     */
    void removeChildByIdGuli(String id);
}
