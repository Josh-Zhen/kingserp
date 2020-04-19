package com.moonlit.kingserp.admin.controller.user;

import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.common.utils.Utils;
import com.moonlit.kingserp.admin.service.LogService;
import com.moonlit.kingserp.admin.service.SysMenuService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysMenuModel;
import com.moonlit.kingserp.entity.admin.SysUser;
import com.moonlit.kingserp.entity.admin.dto.RoleMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author Joshua
 * @since 2020-02-10
 */
@Slf4j
@RestController
@RequestMapping("/sysMenu")
@Api(value = "菜单管理", tags = {"菜单管理"})
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    private LogService logService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 查询当前用户的权限
     * map：User + Menus
     *
     * @return map
     */
    @NeedAuth
    @GetMapping("/selectMenu")
    @ApiOperation("查询当前用户的权限")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    public ResponseObj selectMenu() {
        Map<String, Object> map = new HashMap<>(32);
        try {
            SysUser sysUser = sysUserService.getInfo();
            if (sysUser == null) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10017);
            }
            map = menuService.selectMenu(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(map);
    }

    /**
     * 根據角色Id查詢菜單目錄
     * 設置菜單權限時先調用該接口
     *
     * @param roleId
     * @return
     */
    @NeedAuth
    @GetMapping("/selectMenuByRoleId")
    @ApiOperation("查詢菜單目錄")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "query", dataType = "Integer")
    })
    public ResponseObj selectMenuByRoleId(@RequestParam Integer roleId) {
        SysMenuModel sysMenuList;
        if (null != roleId) {
            sysMenuList = menuService.getCheckedRoleMenus(roleId);
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
        }
        return ResponseObj.createSuccessResponse(sysMenuList);
    }

    /**
     * 添加角色和目錄权限的关系
     *
     * @return
     */
    @NeedAuth
    @PostMapping("/setRoleMenus")
    @ApiOperation("添加角色和目錄权限的关系")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj setRoleMenus(@RequestBody RoleMenu roleMenu) {
        if (!Utils.checkUserIsSuper()) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        try {
            if (null == roleMenu.getRoleId()) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
            }
            if (0 > menuService.addRoleMenu(roleMenu)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("setRoleMenus", "修改角色Id為：" + roleMenu.getRoleId() + " 的權限信息"));
        return ResponseObj.createSuccessResponse();
    }

}