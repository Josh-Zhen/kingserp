package com.moonlit.kingserp.admin.controller.user;

import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.common.annotation.SupperAdminFilter;
import com.moonlit.kingserp.admin.service.LogService;
import com.moonlit.kingserp.admin.service.SysRoleService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色表
 *
 * @author Joshua
 * @since 2020-02-10
 */
@Slf4j
@RestController
@RequestMapping("/sysRole")
@Api(value = "角色", tags = {"角色"})
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysUserService userService;
    @Autowired
    LogService logService;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;


    /**
     * 添加角色
     *
     * @param sysRole
     * @return
     */
    @NeedAuth
    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色")
    public ResponseObj addRole(@RequestBody SysRole sysRole) {
        if (sysRole.getRoleName() != null) {
            // 校驗角色是否存在
            SysRole sysRole1 = roleService.selectName(sysRole.getRoleName());
            if (sysRole1 != null) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10015);
            }
        }
        // 校驗是否是超級管理員
        if (SupperAdminFilter.CheckSupperAdmin()) {
            sysRole.setCreateUserId(userService.getInfo().getId());
            int i = roleService.insert(sysRole);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("addRole", "添加一個名為：" + sysRole.getRoleName() + " 的角色"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 修改角色信息
     *
     * @param sysRole
     * @return
     */
    @NeedAuth
    @PostMapping("/updateRole")
    @ApiOperation("修改角色信息")
    public ResponseObj updateRole(SysRole sysRole) {
        if (SupperAdminFilter.CheckSupperAdmin()) {
            if (sysRole.getRoleId() != null) {
                int i = roleService.updateRole(sysRole);
                if (i < 0) {
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
                }
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateRole", "修改角色ID為：" + sysRole.getRoleId() + " 的角色信息"));
        return ResponseObj.createSuccessResponse();
    }

}

