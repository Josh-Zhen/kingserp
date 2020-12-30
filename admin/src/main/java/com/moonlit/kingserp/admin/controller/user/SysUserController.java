package com.moonlit.kingserp.admin.controller.user;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.common.utils.Utils;
import com.moonlit.kingserp.admin.service.SysLogService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.common.util.CommonUtil;
import com.moonlit.kingserp.common.util.MD5Util;
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
    private SysLogService sysLogService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 管理者登录
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("管理者登录")
    public ResponseObj login(@RequestBody SysUser sysUser) {
        System.out.println("--↓--loginName--↓--" + "\n" + sysUser.getUserName());
        System.out.println("--↓--password--↓--" + "\n" + sysUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword());
        String sessionId;
        Map<String, Object> map = new HashMap<>(2);
        try {
            subject.login(token);

            //设置永不过期
            SecurityUtils.getSubject().getSession().setTimeout(-1000);
            sessionId = subject.getSession().getId().toString();
            System.out.println("--↓--sessionId--↓--" + "\n" + sessionId + "\n---------------------");
            System.out.println();
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
    @ApiOperation("用户登出")
    public ResponseObj userLogout() {
        ShiroUtils.logout();
        ShiroUtils.deleteCache();
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 添加账号
     *
     * @param sysUser
     * @return
     */
    @NeedAuth
    @PostMapping("/addSysUser")
    @ApiOperation("添加账号")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj addSysUser(@RequestBody SysUser sysUser) {
        // 獲取當前操作管理者
        SysUser adminUser = ShiroUtils.getUserInfo();
        if (adminUser.getUserIsSuper() == 1) {
            //判断用户名是否注册
            if (null != sysUserService.checkSysUser(sysUser)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10013);
            }
            sysUser.setCreateUserId(adminUser.getId());
            if (0 > sysUserService.addSysUser(sysUser)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10014);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("addSysUser", "添加賬戶名：" + sysUser.getUserName() + "的账号"));
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
    @ApiOperation("修改成員信息")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj updateSysUser(@RequestBody SysUser sysUser) {
        if (null != sysUser.getId()) {
            // 修改密碼
            if (null != sysUser.getPassword()) {
                String userSalt = CommonUtil.getVerificationCode(6);
                sysUser.setPassword(MD5Util.getMd5insalf(sysUser.getPassword(), userSalt));
                sysUser.setUserSalt(userSalt);
            }
            if (0 > sysUserService.updateSysUser(sysUser)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("updateSysUser", "修改Id為：" + sysUser.getId() + "的成員信息"));
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
    @ApiOperation("刪除成員")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理員Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj delSysUser(@RequestParam Integer id) {
        if (Utils.checkUserIsSuper()) {
            ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        if (null != id) {
            if (0 > sysUserService.delSysUserById(id)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10002);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("delSysUser", "刪除Id為：" + id + "的成員"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 根據關鍵字查詢管理者
     *
     * @param keywords
     * @return
     */
    @NeedAuth
    @GetMapping("/selectSysUsers")
    @ApiOperation("根據關鍵字查詢管理者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "關鍵字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
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
    @ApiOperation("启用/禁用 管理者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUserId", value = "管理員Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "當前狀態", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj updateSysUserStatus(@RequestParam Integer sysUserId, @RequestParam Integer type) {
        if (Utils.checkUserIsSuper()) {
            ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        if (null != sysUserId) {
            SysUser sysUser = new SysUser();
            type = Math.abs(type - 1);
            sysUser.setId(sysUserId);
            sysUser.setStatus(type);
            if (0 > sysUserService.updateSysUser(sysUser)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10014);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10002);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("updateSysUserStatus", "修改成員Id為：" + sysUserId + " 的狀態"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 獲取當前用戶
     *
     * @return
     */
    @NeedAuth
    @GetMapping("/getUserInfo")
    @ApiOperation("獲取當前用戶")
    public ResponseObj getUserInfo() {
        SysUser userInfo = ShiroUtils.getUserInfo();
        return ResponseObj.createSuccessResponse(userInfo);
    }

}