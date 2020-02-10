package com.moonlit.kingserp.admin.common.shiro;

import com.moonlit.kingserp.admin.service.SysRoleService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.entity.admin.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Joshua
 * 1、检查提交的进行认证的令牌信息
 * 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
 * 3、对用户信息进行匹配验证。
 * 4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
 * 5、验证失败则抛出AuthenticationException异常信息。
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 權限校驗
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        return null;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("用户登录认证。。。。。");
        //获取用户的输入的账号
        String username = (String) authenticationToken.getPrincipal();
        SysUser sysUser = sysUserService.getUserInfo(username);
        if (null == sysUser) {
            throw new UnknownAccountException("用户不存在");
        }
        if (sysUser.getStatus() == 0 || sysUser.getState() == 0) {
            throw new UnknownAccountException("用户被禁用");
        }
        // 用戶、密碼、領域(realm name)
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
        // 加鹽
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getUserSalt().getBytes()));
        SecurityUtils.getSubject().getSession().setAttribute("tokenInfo", sysUser);
        return authenticationInfo;
    }
}
