package com.moonlit.kingserp.admin.controller.user;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.common.utils.Utils;
import com.moonlit.kingserp.admin.service.LogService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户 前端控制器
 *
 * @author Joshua
 * @since 2020-02-08
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api(value = "后台成员管理相关操作", tags = {"后台成员管理相关操作"})
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private LogService logService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 管理者登录
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理者登录")
    public ResponseObj login(@RequestBody SysUser sysUser) {
        System.out.println("--↓--loginName--↓--");
        System.out.println(sysUser.getUserName());
        System.out.println("--↓--password--↓--");
        System.out.println(sysUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword());
        String sessionId;
        Map<String, Object> map = new HashMap<>(2);
        try {
            subject.login(token);

            //设置永不过期
            SecurityUtils.getSubject().getSession().setTimeout(-1000);
            sessionId = subject.getSession().getId().toString();
            System.out.println("--↓--sessionId--↓--");
            System.out.println(sessionId);
            System.out.println("---------------------");
            SysUser user = sysUserService.getUserInfo(sysUser.getUserName());
            //设置最后登录时间
            SysUser syUser = new SysUser();
            syUser.setId(user.getId());
            syUser.setLastTime(new Date());
            sysUserService.updateSysUser(syUser);

            map.put("sysUserInfo", user);
            map.put("token", sessionId);
            return ResponseObj.createSuccessResponse(map);
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return ResponseObj.createErrResponse("密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseObj.createErrResponse(e.getMessage());
        }
    }

    /**
     * 用户登出.並清除緩存
     */
    @GetMapping("/logout")
    @ApiOperation(value = "用户登出")
    public ResponseObj userLogout() {
        ShiroUtils.logout();
        ShiroUtils.deleteCache();
        return ResponseObj.createSuccessResponse("用戶已登出");
    }

    /**
     * 添加账号
     *
     * @param sysUser
     * @return
     */
    @NeedAuth
    @PostMapping("/addSysUser")
    @ApiOperation(value = "添加账号")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj addSysUser(@RequestBody SysUser sysUser) {
        // 獲取當前操作管理者
        SysUser adminUser = ShiroUtils.getUserInfo();
        if (adminUser.getUserIsSuper() == 1) {
            //判断用户名是否注册
            SysUser sysUserInfo = sysUserService.exitSysUser(sysUser);
            if (sysUserInfo != null) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10013);
            }
            sysUser.setCreateUserId(adminUser.getId());
            int i = sysUserService.addSysUser(sysUser);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10014);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("addSysUser", "添加账号，賬戶名：" + sysUser.getUserName()));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 修改成員信息
     *
     * @param sysUser
     * @return
     */
    @NeedAuth
    @PutMapping("/updateSysUser")
    @ApiOperation(value = "修改成員信息")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj updateSysUser(@RequestBody SysUser sysUser) {
        if (null != sysUser.getId()) {
            int i = sysUserService.updateSysUser(sysUser);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateSysUser", "修改成員信息，Id為：" + sysUser.getId()));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 刪除成員
     *
     * @param id
     * @return
     */
    @NeedAuth
    @DeleteMapping("/delSysUser")
    @ApiOperation(value = "刪除成員")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理員Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj delSysUser(@RequestParam Integer id) {
        if (Utils.checkUserIsSuper()) {
            if (null != id) {
                int i = sysUserService.delSysUserById(id);
                if (i < 0) {
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
                }
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10002);
            }
        } else {
            ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("delSysUser", "刪除一個管理員，Id為：" + id));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 根據關鍵字查詢管理者
     *
     * @param keywords
     * @return
     */
    @GetMapping("/selectSysUsers")
    @ApiOperation(value = "根據關鍵字查詢管理者")
    @ApiImplicitParam(name = "keywords", value = "關鍵字", paramType = "query", dataType = "String")
    public ResponseObj selectSysUsers(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                      @RequestParam(required = false) String keywords) {
        PageInfo<SysUser> sysUsers = null;
        try {
            sysUsers = sysUserService.selectSysUsers(currentPage, pageSize, keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(sysUsers);
    }

    /**
     * 启用/禁用 管理者
     */
    @NeedAuth
    @PutMapping("/updateSysUserStatus")
    @ApiOperation(value = "启用/禁用 管理者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUserId", value = "管理員Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "當前狀態", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj updateSysUserStatus(@RequestParam Integer sysUserId, @RequestParam Integer type) {
        if (Utils.checkUserIsSuper()) {
            int i = 0;
            if (null != sysUserId) {
                SysUser sysUser = new SysUser();
                type = Math.abs(type - 1);
                sysUser.setId(sysUserId);
                sysUser.setStatus(type);
                i = sysUserService.updateSysUser(sysUser);
            }
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10014);
            }
        } else {
            ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateSysUserStatus", "修改管理者Id為：" + sysUserId + " 的狀態"));
        return ResponseObj.createSuccessResponse();
    }

}