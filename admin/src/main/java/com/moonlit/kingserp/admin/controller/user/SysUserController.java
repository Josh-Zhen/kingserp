package com.moonlit.kingserp.admin.controller.user;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.common.utils.Utils;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "后台成员管理相关操作", tags = {"后台成员管理相关操作"})
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 后台用户登录
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "后台用户登录")
    public ResponseObj login(@RequestBody SysUser sysUser) {
        System.out.println("-----loginName----" + sysUser.getUserName());
        System.out.println("-----password----" + sysUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword());
        String sessionId;
        Map<String, Object> map = new HashMap<>(2);
        try {
            subject.login(token);

            //设置永不过期
            SecurityUtils.getSubject().getSession().setTimeout(-1000);
            sessionId = subject.getSession().getId().toString();
            System.out.println("------sessionId----" + sessionId);

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
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 修改成員信息
     *
     * @param sysUser
     * @return
     */
    @NeedAuth
    @PostMapping("/updateSysUser")
    @ApiOperation(value = "修改成員信息")
    public ResponseObj updateSysUser(@RequestBody SysUser sysUser) {
        if (null != sysUser.getId()) {
            int i = sysUserService.updateSysUser(sysUser);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 刪除成員
     *
     * @param sysUserId
     * @return
     */
    @NeedAuth
    @PostMapping("/delSysUser")
    @ApiOperation(value = "刪除成員")
    public ResponseObj delSysUser(@RequestParam Integer sysUserId) {
        if (Utils.checkUserIsSuper()) {
            if (null != sysUserId) {
                int i = sysUserService.delSysUserById(sysUserId);
                if (i < 0) {
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
                }
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10002);
            }
        } else {
            ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
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
    public ResponseObj selectSysUsers(@RequestBody(required = false) String keywords) {
        PageInfo<SysUser> pageInfo = null;
        try {
            pageInfo = new PageInfo(sysUserService.selectSysUsers(keywords));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(pageInfo);
    }

    /**
     * 启用/禁用 管理者
     */
    @NeedAuth
    @PostMapping("/updateSysUserStatus")
    @ApiOperation(value = "启用/禁用 管理者")
    public ResponseObj updateSysUserStatus(@RequestParam Integer sysUserId, @RequestParam Integer type) {
        if (Utils.checkUserIsSuper()) {
            SysUser sysUser = new SysUser();
            int i = 0;
            if (null != sysUserId) {
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
        return ResponseObj.createSuccessResponse();
    }

}