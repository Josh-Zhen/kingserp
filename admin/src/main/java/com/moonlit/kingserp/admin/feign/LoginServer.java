package com.moonlit.kingserp.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远端调用
 * @Author: Joshua
 * @CreateTime: 2020-02-04
 **/
@FeignClient("Login-Server")
public interface LoginServer {

}
