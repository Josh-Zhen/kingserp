package com.moonlit.kingserp.admin.service;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.entity.admin.SysUser;

/**
 * 系统用户 服务类
 *
 * @author Joshua
 * @since 2020-02-08
 */
public interface SysUserService {

    /**
     * 從中session获取用户信息
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
    SysUser checkSysUser(SysUser sysUser);

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
     * @param currentPage
     * @param pageSize
     * @param keywords
     * @return
     */
    PageInfo<SysUser> selectSysUsers(Integer currentPage, Integer pageSize, String keywords);

    /**
     * 獲取當前用戶
     *
     * @param userName
     * @return
     */
    SysUser getUserInfo(String userName);
}
