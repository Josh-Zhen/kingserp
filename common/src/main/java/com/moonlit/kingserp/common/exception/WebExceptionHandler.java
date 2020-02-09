package com.moonlit.kingserp.common.exception;

import com.moonlit.kingserp.common.response.ResponseObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author Administrator
 * @create 2019-04-27 17:32
 **/
@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseObj handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseObj.createResponse(500, "系统异常");
    }

    @ExceptionHandler(EpException.class)
    public ResponseObj handleEpException(EpException e) {
        log.error(e.getMessage(), e);
        return ResponseObj.createResponse(e.getCode(), e.getMessage());
    }

    /**
     * 权限不足报错拦截
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseObj unauthorizedExceptionHandler(UnauthorizedException e) throws Exception {
        log.error(e.getMessage(), e);
        return ResponseObj.createResponse(401, "权限不足");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseObj handleAuthorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return ResponseObj.createErrResponse("没有权限，请联系管理员授权");
    }
}
