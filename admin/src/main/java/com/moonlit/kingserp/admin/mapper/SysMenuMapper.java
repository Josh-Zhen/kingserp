package com.moonlit.kingserp.admin.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.admin.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单管理 Mapper 接口
 *
 * @author Joshua
 * @since 2020-02-10
 */
public interface SysMenuMapper extends MyMapper<SysMenu> {

    /**
     * 根據角色id查詢權限
     *
     * @param roleId
     * @return
     */
    @Select("")
    List<SysMenu> getSysMenuByRolesId(Integer roleId);
}
