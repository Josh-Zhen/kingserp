package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.mapper.SysRoleMapper;
import com.moonlit.kingserp.admin.service.SysRoleService;
import com.moonlit.kingserp.entity.admin.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Joshua
 * @since 2020-02-10
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 根據用戶Id查詢是否具備權限
     *
     * @param id
     * @return
     */
    @Override
    public SysRole getSysRoleByUserId(Integer id) {
        return roleMapper.getSysRoleByUserId(id);
    }
}
