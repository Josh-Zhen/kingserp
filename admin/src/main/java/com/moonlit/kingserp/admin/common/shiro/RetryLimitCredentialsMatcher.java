package com.moonlit.kingserp.admin.common.shiro;

import com.alibaba.fastjson.JSON;
import com.moonlit.kingserp.common.util.RedisUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Joshua
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    String key = "sysUserPassword:";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        int pwdErrorSize = 10;
        int pwdLockTime = 10;
        String username = (String) token.getPrincipal();
        String number = JSON.toJSONString(redisUtil.get(key + username));
        int retryCount = 0;
        if (!"null".equals(number)) {
            retryCount = (Integer.parseInt(number));
        }
        //动态设置密码错误次数
        if (retryCount >= pwdErrorSize) {
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            redisUtil.del(key + username);
        } else {
            retryCount += 1;
            //动态设置锁定时间
            redisUtil.set(key + username, retryCount, pwdLockTime * 3600);
        }
        return matches;
    }
}
