package com.moonlit.kingserp.admin.common.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义获取Token
 *
 * @author Joshua
 */
public class MySessionManager extends DefaultWebSessionManager {
    private static final String TOKEN = "token";

    public MySessionManager() {
        super();
        // 是否定時檢查session
        this.setSessionValidationSchedulerEnabled(true);
        // 刪除過期的session
        this.setDeleteInvalidSessions(true);
    }

    /**
     * 每次请求进来,Shiro会去从请求头找token这个key对应的Value(Token)
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getParameter(TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = WebUtils.toHttp(request).getHeader(TOKEN);
        }
        if (!StringUtils.isEmpty(token)) {
            return token;
        } else {
            return super.getSessionId(request, response);
        }
    }
}
