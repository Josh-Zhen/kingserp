package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.mapper.SysMenuMapper;
import com.moonlit.kingserp.admin.mapper.SysRoleMapper;
import com.moonlit.kingserp.admin.mapper.SysRoleMenuMapper;
import com.moonlit.kingserp.admin.service.SysMenuService;
import com.moonlit.kingserp.entity.admin.*;
import com.moonlit.kingserp.entity.admin.dto.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理 服务实现类
 *
 * @author Joshua
 * @since 2020-02-10
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 根據角色id查詢權限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> getSysMenuByRolesId(Integer roleId) {
        List<SysMenu> menus = new ArrayList<>();
        try {
            menus = menuMapper.getSysMenuByRolesId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    /**
     * 查询当前用户的权限
     *
     * @param sysUser
     * @return
     */
    @Override
    public Map<String, Object> selectMenu(SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(32);
        map.put("sysUserInfo", sysUser);
        SysRole sysRole = roleMapper.getSysRole(sysUser.getId());
        if (sysRole == null) {
            map.put("sysMenu", menuMapper.getSysMenuByRolesId(null));
        } else {
            map.put("sysMenu", menuMapper.getSysMenuByRolesId(sysRole.getRoleId()));
        }
        return map;
    }

    /**
     * 查詢所有目錄，根據角色id查詢目錄啓用情況（0.否 1.是）
     *
     * @param roleId
     * @return
     */
    @Override
    public SysMenuModel getCheckedRoleMenus(Integer roleId) {
        SysMenuModel roleMenus = null;
        try {
            roleMenus = menuMapper.getCheckedRoleMenus(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleMenus;
    }

    /**
     * 添加角色和权限的关系
     *
     * @param roleMenu
     * @return
     */
    @Override
    public int addRoleMenu(RoleMenu roleMenu) {
        int i = 0;
        //刪除
        removeByRoleId(roleMenu.getRoleId());
        //添加
        if (!StringUtils.isEmpty(roleMenu.getMenuIdList())) {
            String[] resourcesArr = roleMenu.getMenuIdList().split(",");
            if (resourcesArr.length == 0) {
                return 0;
            }
            List<SysRoleMenu> roleResources = new ArrayList<>();
            for (String menu : resourcesArr) {
                if (StringUtils.isEmpty(menu)) {
                    continue;
                }
                SysRoleMenu r = new SysRoleMenu();
                r.setRoleId(roleMenu.getRoleId());
                r.setMenuId(Integer.parseInt(menu));
                roleResources.add(r);
            }
            if (roleResources.size() == 0) {
                return 0;
            }
            roleMenuMapper.insertList(roleResources);
        }
        return i;
    }

    /**
     * 批量刪除
     *
     * @param roleId
     */
    public void removeByRoleId(Integer roleId) {
        //删除
        Example example = new Example(SysRoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        roleMenuMapper.deleteByExample(example);
    }
}
