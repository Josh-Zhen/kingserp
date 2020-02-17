package com.moonlit.kingserp.admin.common.shiro;

import com.moonlit.kingserp.entity.admin.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @Description: Shiro工具類
 * @Author: Joshua
 * @CreateDate: 2020-02-17 21:18
 * @Version 1.0
 */
public class ShiroUtils {
    /**
     * 私有构造器
     **/
    private ShiroUtils() {
    }

    /**
     * 获取当前用户Session
     *
     * @return SysUserSession
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static SysUser getUserInfo() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     *
     * @Return void
     */
    public static void deleteCache() {
        SecurityUtils.getSubject().getSession().setAttribute(null, null);
    }

}
