package com.moonlit.kingserp.admin.feign;

import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.LyUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 远端调用
 * @Author: Joshua
 * @CreateTime: 2020-02-04
 **/
@FeignClient("Login-Server")
public interface LoginServer {

    //---------------------------客戶信息---------------------------/

    /**
     * 新增一個客戶
     *
     * @param user
     * @return
     */
    @PostMapping("user/insetUser")
    @ApiOperation(value = "添加客戶")
    ResponseObj insetUser(@RequestBody LyUser user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("user/updateUser")
    @ApiOperation(value = "修改客戶信息")
    ResponseObj updateUser(@RequestBody LyUser user);

    /**
     * 刪除用戶
     *
     * @param userId
     * @return
     */
    @DeleteMapping("user/deleteUser")
    @ApiOperation(value = "删除客戶")
    ResponseObj deleteUser(@RequestParam Integer userId);

    /**
     * 根據關鍵字查詢用戶
     *
     * @param keywords
     * @return
     */
    @GetMapping("user/getUsers")
    @ApiOperation(value = "根據關鍵字查詢客戶")
    ResponseObj getUsers(@RequestParam(required = false) String keywords);
}
