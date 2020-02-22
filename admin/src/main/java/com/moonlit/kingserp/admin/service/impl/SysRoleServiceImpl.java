package com.moonlit.kingserp.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.mapper.SysRoleMapper;
import com.moonlit.kingserp.admin.service.SysRoleService;
import com.moonlit.kingserp.entity.admin.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    /**
     * 校驗角色是否存在
     *
     * @param roleName
     * @return
     */
    @Override
    public SysRole selectName(String roleName) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        return roleMapper.selectOne(sysRole);
    }

    /**
     * 添加一個角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public int insert(SysRole sysRole) {
        sysRole.setCreateTime(new Date());
        return roleMapper.insert(sysRole);
    }

    /**
     * 更新角色信息
     *
     * @param sysRole
     * @return
     */
    @Override
    public int updateRole(SysRole sysRole) {
        return roleMapper.updateByPrimaryKeySelective(sysRole);
    }

    /**
     * 刪除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public int delectRole(Integer roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(roleId);
        return roleMapper.delete(sysRole);
    }

    /**
     * 查詢角色
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SysRole> selectRoles(Integer currentPage, Integer pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(currentPage, pageSize);
            pageInfo = new PageInfo(roleMapper.selectRoles());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
