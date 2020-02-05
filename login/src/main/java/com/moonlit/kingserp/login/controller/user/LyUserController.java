package com.moonlit.kingserp.login.controller.user;

import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.common.util.ChineseToEnUtil;
import com.moonlit.kingserp.entity.login.LyUser;
import com.moonlit.kingserp.login.service.LyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * 客戶信息
 *
 * @author Joshua
 * @since 2020-02-04
 */
@Slf4j
@RestController
@Api(value = "用户信息", tags = {"用户信息"})
@RequestMapping("/user")
public class LyUserController {

    @Autowired
    private LyUserService userService;

    /************************************************** admin 調用 ******************************************/

    /**
     * 新增一個客戶
     *
     * @param user
     * @return
     */
    @PostMapping("/insetUser")
    @ApiOperation(value = "新增一個客戶")
    public ResponseObj insetUser(@RequestBody LyUser user) {
        int i = 0;
        if (user.getUserName() != null && !user.getUserName().isEmpty()) {
            // 判斷該用戶是否存在
            if (0 == userService.seleUserByUserName(user.getUserName())) {
                user.setRegistrationTime(new Date());
                String nameShorthand = ChineseToEnUtil.getPinYinHeadChar(user.getNickName());
                user.setNameShorthand(nameShorthand);
                userService.insetUser(user);
            } else {
                return ResponseObj.createErrResponse("該客戶已經存在!!");
            }
        } else {
            return ResponseObj.createErrResponse("請輸入客戶名稱!!");
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户信息")
    public ResponseObj updateUser(LyUser user) {
        try {
            if (user.getId() != null) {
                userService.updateUser(user);
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20506);
            }
        } catch (Exception e) {
            log.error("修改用户信息失敗", e);
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 刪除用戶
     *
     * @param userId
     * @return
     */
    @GetMapping("/deleteUser")
    @ApiOperation(value = "删除用户")
    public ResponseObj deleteUser(Integer userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            log.error("删除用户", e);
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 根據關鍵字查詢用戶
     *
     * @param
     * @return user
     */
    @GetMapping("/getUsers")
    @ApiOperation(value = "根據關鍵字查詢用戶")
    @ApiImplicitParam(name = "Keywords", value = "關鍵字", paramType = "query", dataType = "String")
    public ResponseObj getUsers(@RequestBody(required = false) String Keywords) {
        //查询用户信息
        ArrayList<LyUser> userInfo = null;
        try {
            userInfo = userService.getUserByUserKeywords(Keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(userInfo);
    }

}

