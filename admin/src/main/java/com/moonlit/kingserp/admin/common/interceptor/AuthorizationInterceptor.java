package com.moonlit.kingserp.admin.common.interceptor;

import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.exception.EpException;
import com.moonlit.kingserp.entity.admin.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限（token）验证
 *
 * @author jun
 * @create 2019-04-27 20:47
 **/
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SysUserService userService;
    public static final String LOGIN_TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-URL-PATH");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        NeedAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(NeedAuth.class);
        } else {
            return true;
        }

        //如果有@NeedAuth注解，则要验证token
        if (annotation == null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }

        //tok为空
        if (StringUtils.isBlank(token)) {
            throw new EpException("请先登录", 401);
        }

        //查询token信息
        SysUser user = userService.getInfo();
        if (null == user) {
            throw new EpException("token失效，请重新登录", 401);
        }
        return true;
    }
}
