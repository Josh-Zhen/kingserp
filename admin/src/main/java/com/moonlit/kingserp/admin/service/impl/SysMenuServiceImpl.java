package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.mapper.SysMenuMapper;
import com.moonlit.kingserp.admin.service.SysMenuService;
import com.moonlit.kingserp.entity.admin.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param userName
     * @return
     */
    @Override
    public Map<String, Object> selectMenu(String userName) {
        Map<String, Object> map = new HashMap<>();

        return map;
    }
}
