package com.moonlit.kingserp.admin.controller.user;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.common.utils.Utils;
import com.moonlit.kingserp.admin.service.LogService;
import com.moonlit.kingserp.admin.service.SysRoleService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

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
        if (Utils.checkUserIsSuper()) {
            sysRole.setCreateUserId(userService.getInfo().getSysUserId());
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
    @PutMapping("/updateRole")
    @ApiOperation("修改角色信息")
    public ResponseObj updateRole(@RequestBody SysRole sysRole) {
        if (Utils.checkUserIsSuper()) {
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

    /**
     * 修改角色狀態（0.禁用 1.存活）
     *
     * @param roleId
     * @param state
     * @return
     */
    @NeedAuth
    @PutMapping("/updateRoleState")
    @ApiOperation("修改角色狀態")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "state", value = "狀態", paramType = "query", dataType = "Integer")
    })
    public ResponseObj updateRoleState(@RequestParam Integer roleId, @RequestParam Integer state) {
        if (Utils.checkUserIsSuper()) {
            if (null != roleId) {
                int i;
                SysRole role = new SysRole();
                state = Math.abs(state - 1);
                role.setRoleId(roleId);
                role.setState(state);
                i = roleService.updateRole(role);
                if (i < 0) {
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
                }
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateRoleState", "修改角色ID為：" + roleId + " 的狀態"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 刪除角色
     *
     * @param roleId
     * @return
     */
    @NeedAuth
    @DeleteMapping("/delectRole")
    @ApiOperation("刪除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "query", dataType = "Integer")
    })
    public ResponseObj delectRole(@RequestParam Integer roleId) {
        if (Utils.checkUserIsSuper()) {
            int i;
            if (null != roleId) {
                i = roleService.delectRole(roleId);
                if (i < 0) {
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
                }
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("delectRole", "管理者Id：" + ShiroUtils.getUserInfo().getSysUserId() + " 刪除一個管理員，Id為：" + roleId));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 查詢角色
     *
     * @return
     */
    @GetMapping("/selectRoles")
    @ApiOperation("查詢角色")
    public ResponseObj selectRoles(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageInfo<SysRole> sysUserPageInfo = null;
        try {
            sysUserPageInfo = roleService.selectRoles(currentPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(sysUserPageInfo);
    }
}

