package com.moonlit.kingserp.admin.common.annotation;

import java.lang.annotation.*;

/**
 * 需要Token验证
 * @author Administrator
 * @create 2019-04-27 18:06
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedAuth {
}
