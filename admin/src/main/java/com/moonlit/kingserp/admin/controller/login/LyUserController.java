package com.moonlit.kingserp.admin.controller.login;

import com.moonlit.kingserp.admin.feign.LoginServer;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.LyUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-04-05 21:19
 * @Version 1.0
 */
@Slf4j
@RestController
@Api(value = "客戶信息", tags = {"客戶信息"})
@RequestMapping("/user")
public class LyUserController {
    @Autowired
    LoginServer loginServer;

    /**
     * 新增一個客戶
     *
     * @param user
     * @return
     */
    @PostMapping("/insetUser")
    @ApiOperation(value = "新增一個客戶")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj insetUser(@RequestBody LyUser user) {
        return loginServer.insetUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    @ApiOperation(value = "修改用户信息")
    public ResponseObj updateUser(@RequestBody LyUser user) {
        return loginServer.updateUser(user);
    }

    /**
     * 刪除用戶
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "删除用户")
    public ResponseObj deleteUser(@RequestParam Integer userId) {
        return loginServer.deleteUser(userId);
    }

    /**
     * 根據關鍵字查詢用戶
     *
     * @param
     * @return user
     */
    @GetMapping("/getUsers")
    @ApiOperation(value = "根據關鍵字查詢用戶")
    @ApiImplicitParam(name = "keywords", value = "關鍵字", paramType = "query", dataType = "String")
    public ResponseObj getUsers(@RequestBody(required = false) String keywords) {
        return loginServer.getUsers(keywords);
    }
}
