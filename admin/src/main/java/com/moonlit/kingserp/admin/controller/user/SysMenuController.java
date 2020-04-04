package com.moonlit.kingserp.admin.controller.user;

import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.service.SysMenuService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysMenu;
import com.moonlit.kingserp.entity.admin.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 查询当前用户的权限
     * map：User + Menus
     *
     * @return map
     */
    @NeedAuth
    @GetMapping("/selectMenu")
    @ApiOperation(value = "查询当前用户的权限")
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
     * @param roleId
     * @return
     */
    @NeedAuth
    @GetMapping("/selectMenuByRoleId")
    @ApiOperation(value = "查詢菜單目錄")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "roleId", value = "角色Id", paramType = "query", dataType = "Integer")
    })
    public ResponseObj selectMenuByRoleId(@RequestParam Integer roleId) {
        List<SysMenu> sysMenuList;
        if (null != roleId) {
            sysMenuList = menuService.getCheckedRoleMenus(roleId);
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10016);
        }
        return ResponseObj.createSuccessResponse(sysMenuList);
    }

}

