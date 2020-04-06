package com.moonlit.kingserp.admin.controller.login;

import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.feign.LoginServer;
import com.moonlit.kingserp.admin.service.LogService;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.LyUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
@RequestMapping("/lyUser")
public class LyUserController {

    @Autowired
    LoginServer loginServer;
    @Autowired
    private LogService logService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 新增一個客戶
     *
     * @param user
     * @return
     */
    @NeedAuth
    @PostMapping("/insetUser")
    @ApiOperation(value = "添加客戶")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj insetUser(@RequestBody LyUser user) {
        ResponseObj responseObj = loginServer.insetUser(user);
        threadPoolTaskExecutor.execute(() -> logService.addLog("insetUser", "添加客戶名：" + user.getUserName() + "的客戶"));
        return responseObj;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @NeedAuth
    @PutMapping("/updateUser")
    @ApiOperation(value = "修改客戶信息")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj updateUser(@RequestBody LyUser user) {
        ResponseObj responseObj = loginServer.updateUser(user);
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateUser", "修改客戶信息id：" + user.getId() + "的客戶"));
        return responseObj;
    }

    /**
     * 刪除用戶
     *
     * @param userId
     * @return
     */
    @NeedAuth
    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "删除客戶")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "客户id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj deleteUser(@RequestParam Integer userId) {
        ResponseObj responseObj = loginServer.deleteUser(userId);
        threadPoolTaskExecutor.execute(() -> logService.addLog("deleteUser", "删除客戶id：" + userId + "的客戶"));
        return responseObj;
    }

    /**
     * 根據關鍵字查詢用戶
     * 匹配id、客户账号、昵称、手机号码、賬號拼音簡寫
     *
     * @param
     * @return user
     */
    @NeedAuth
    @GetMapping("/getUsers")
    @ApiOperation(value = "根據關鍵字查詢客戶")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "關鍵字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj getUsers(@RequestParam(required = false) String keywords) {
        return loginServer.getUsers(keywords);
    }

    /**
     * 啓用/禁用 客戶
     *
     * @param id
     * @param type
     * @return
     */
    @NeedAuth
    @PutMapping("/updateUserType")
    @ApiOperation("啓用/禁用 客戶")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "客戶id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "當前狀態", paramType = "query", dataType = "Integer")
    })
    public ResponseObj updateUserType(@RequestParam Integer id, @RequestParam Integer type) {
        ResponseObj responseObj = loginServer.updateUserType(id, type);
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateUserType", "修改客戶id：" + id + "的狀態"));
        return responseObj;
    }
}
