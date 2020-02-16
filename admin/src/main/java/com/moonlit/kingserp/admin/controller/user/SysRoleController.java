package com.moonlit.kingserp.admin.controller.user;

import com.moonlit.kingserp.admin.service.SysRoleService;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.SysRole;
import com.moonlit.kingserp.entity.admin.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 添加角色
     *
     * @param sysRole
     * @return
     */
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
        SysUser sysUser = userService.getInfo();
        // 校驗是否是超級管理員
        if (sysUser.getUserIsSuper() == 1) {
            sysRole.setCreateUserId(sysUser.getId());
            int i = roleService.insert(sysRole);
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10008);
        }
        return ResponseObj.createSuccessResponse();
    }
}

