package com.moonlit.kingserp.admin.controller.user;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 添加账号
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/addSysUser")
    @ApiOperation(value = "添加账号")
    public ResponseObj addSysUser(@RequestBody SysUser sysUser) {
        SysUser admin = sysUserService.getInfo();
        //判断用户名是否注册
        SysUser sysUserInfo = sysUserService.exitSysUser(sysUser);
        if (sysUserInfo != null) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10013);
        }
        int i = sysUserService.addSysUser(sysUser);
        if (i < 0) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10014);
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 修改成員信息
     *
     * @param sysUser
     * @return
     */
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
    @PostMapping("/delSysUser")
    @ApiOperation(value = "刪除成員")
    public ResponseObj delSysUser(@RequestParam Integer sysUserId) {
        if (null != sysUserId) {
            int i = sysUserService.delSysUserById(sysUserId);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10002);
        }
        return ResponseObj.createSuccessResponse();
    }

    @GetMapping("/selectSysUsers")
    @ApiOperation(value = "根據關鍵字查詢用戶")
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
}

