package com.moonlit.kingserp.admin.shiro;

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
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
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
        //获取用户的输入的账号.
        String username = (String) authenticationToken.getPrincipal();
        SysUser sysUser = sysUserService.getUserInfo(username);
        if (null == sysUser) {
            throw new UnknownAccountException("用户不存在");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户
                sysUser,
                //密码
                sysUser.getPassword(),
                //realm name
                getName()
        );
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(sysUser.getUserSalt().getBytes()));
        SecurityUtils.getSubject().getSession().setAttribute("tokenInfo", sysUser);
        return authenticationInfo;
    }

}
