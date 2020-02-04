package com.moonlit.kingserp.login.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 远端调用
 * @Author: Joshua
 * @CreateTime: 2020-02-04
 **/
@FeignClient("Admin-Server")
public interface AdminServer {

}
