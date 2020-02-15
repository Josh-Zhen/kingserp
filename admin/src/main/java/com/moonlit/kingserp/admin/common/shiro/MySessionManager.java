package com.moonlit.kingserp.admin.common.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author Joshua
 */
public class MySessionManager extends DefaultWebSessionManager {
    private static final String TOKEN = "token";

    public MySessionManager(){
        super();
    }
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token= WebUtils.toHttp(request).getParameter(TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = WebUtils.toHttp(request).getHeader(TOKEN);
        }
        if (!StringUtils.isEmpty(token)){
            return token;
        }else {
            return super.getSessionId(request, response);
        }
    }
}
