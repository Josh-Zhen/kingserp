package com.moonlit.kingserp.admin.service;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.entity.admin.SysRole;

/**
 * 角色 服务类
 *
 * @author Joshua
 * @since 2020-02-10
 */
public interface SysRoleService {

    /**
     * 根據用戶Id查詢是否具備權限
     *
     * @param id
     * @return
     */
    SysRole getSysRoleByUserId(Integer id);

    /**
     * 校驗角色是否存在
     *
     * @param roleName
     * @return
     */
    SysRole selectName(String roleName);

    /**
     * 添加一個角色
     *
     * @param sysRole
     * @return
     */
    int insert(SysRole sysRole);

    /**
     * 更新角色信息
     *
     * @param sysRole
     * @return
     */
    int updateRole(SysRole sysRole);

    /**
     * 刪除角色
     *
     * @param roleId
     * @return
     */
    int delectRole(Integer roleId);

    /**
     * 查詢角色
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<SysRole> selectRoles(Integer currentPage, Integer pageSize);
}
