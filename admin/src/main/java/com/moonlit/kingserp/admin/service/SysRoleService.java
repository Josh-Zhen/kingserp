package com.moonlit.kingserp.admin.service;

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
}
