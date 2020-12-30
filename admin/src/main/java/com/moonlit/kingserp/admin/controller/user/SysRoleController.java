package com.moonlit.kingserp.admin.controller.user;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.common.utils.Utils;
import com.moonlit.kingserp.admin.service.SysLogService;
import com.moonlit.kingserp.admin.service.SysRoleService;
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
    private SysLogService sysLogService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 添加角色
     *
     * @param sysRole
     * @return
     */
    @NeedAuth
    @PostMapping("/addRole")
    @ApiOperation("添加角色")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj addRole(@RequestBody SysRole sysRole) {
        if (sysRole.getRoleName() != null) {
            // 校驗角色是否存在
            SysRole sysRole1 = roleService.selectName(sysRole.getRoleName());
            if (sysRole1 != null) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10015);
            }
            // 校驗是否是超級管理員
            if (Utils.checkUserIsSuper()) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
            }
            sysRole.setCreateUserId(ShiroUtils.getUserInfo().getId());
            if (0 > roleService.insert(sysRole)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("addRole", "添加一個名為：" + sysRole.getRoleName() + " 的角色"));
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
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj updateRole(@RequestBody SysRole sysRole) {
        if (Utils.checkUserIsSuper()) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        if (sysRole.getRoleName() != null) {
            // 校驗角色是否存在
            if (roleService.selectName(sysRole.getRoleName()) != null) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10015);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
        }

        if (sysRole.getRoleId() != null) {
            if (0 > roleService.updateRole(sysRole)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("updateRole", "修改角色Id為：" + sysRole.getRoleId() + " 的角色信息"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 启用/禁用 角色（0.禁用 1.存活）
     *
     * @param roleId
     * @param state
     * @return
     */
    @NeedAuth
    @PutMapping("/updateRoleState")
    @ApiOperation("启用/禁用 角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "state", value = "當前狀態", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj updateRoleState(@RequestParam Integer roleId, @RequestParam Integer state) {
        if (Utils.checkUserIsSuper()) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        if (null != roleId) {
            SysRole role = new SysRole();
            state = Math.abs(state - 1);
            role.setRoleId(roleId);
            role.setState(state);
            if (0 > roleService.updateRole(role)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("updateRoleState", "修改角色Id為：" + roleId + " 的狀態"));
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
            @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj delectRole(@RequestParam Integer roleId) {
        if (Utils.checkUserIsSuper()) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        if (null != roleId) {
            if (0 > roleService.delectRole(roleId)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
        }
        threadPoolTaskExecutor.execute(() -> sysLogService.addLog("delectRole", "刪除角色Id為：" + roleId + " 的角色"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 查詢角色
     *
     * @return
     */
    @NeedAuth
    @GetMapping("/selectRoles")
    @ApiOperation("查詢角色")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
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

