package com.moonlit.kingserp.admin.common.annotation;

import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.entity.admin.SysUser;

/**
 * @Description: 判定用戶是否是超級管理員
 * @Author: Joshua
 * @CreateDate: 2020-02-21 23:04
 * @Version 1.0
 */
public class SupperAdminFilter {

    /**
     * 是否是超級管理者
     *
     * @return
     */
    public static Boolean CheckSupperAdmin() {
        SysUser sysUser = ShiroUtils.getUserInfo();
        return sysUser.getUserIsSuper() == 1;
    }
}
