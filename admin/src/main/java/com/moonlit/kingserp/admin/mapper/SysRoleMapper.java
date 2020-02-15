package com.moonlit.kingserp.admin.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.admin.SysRole;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author Joshua
 * @since 2020-02-10
 */
public interface SysRoleMapper extends MyMapper<SysRole> {

    /**
     * 根據用戶Id查詢是否具備權限
     *
     * @param id
     * @return
     */
    @Select("SELECT r.* FROM sys_user u INNER JOIN sys_user_role ur ON ( u.id = ur.user_id ) INNER JOIN sys_role r ON ( ur.role_id = r.role_id ) WHERE u.id = #{id}")
    SysRole getSysRoleByUserId(Integer id);
}
