package com.moonlit.kingserp.admin.service;

import com.moonlit.kingserp.entity.admin.SysUser;

import java.util.ArrayList;

/**
 * 系统用户 服务类
 *
 * @author Joshua
 * @since 2020-02-08
 */
public interface SysUserService {

    /**
     * 当前登录用户的权限等信息
     *
     * @return SysUser
     */
    SysUser getInfo();

    /**
     * 校驗用戶是否存在
     *
     * @param sysUser
     * @return SysUser
     */
    SysUser exitSysUser(SysUser sysUser);

    /**
     * 添加一個成員
     *
     * @param sysUser
     * @return
     */
    int addSysUser(SysUser sysUser);

    /**
     * 修改成員信息
     *
     * @param sysUser
     * @return
     */
    int updateSysUser(SysUser sysUser);

    /**
     * 刪除用戶
     *
     * @param sysUserId
     * @return
     */
    int delSysUserById(Integer sysUserId);

    /**
     * 模糊查詢管理者
     *
     * @param keywords
     * @return
     */
    ArrayList<SysUser> selectSysUsers(String keywords);
}
