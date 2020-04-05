package com.moonlit.kingserp.login.controller.user;

import com.github.pagehelper.PageInfo;
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

import java.util.Date;

/**
 * 客戶信息
 *
 * @author Joshua
 * @since 2020-02-04
 */
@Slf4j
@RestController
@Api(value = "客戶信息", tags = {"客戶信息"})
@RequestMapping("/user")
public class LyUserController {

    @Autowired
    private LyUserService userService;

    /**
     * 新增一個客戶
     *
     * @param user
     * @return
     */
    @PostMapping("/insetUser")
    @ApiOperation(value = "添加客戶")
    public ResponseObj insetUser(@RequestBody LyUser user) {
        if (user.getUserName() != null && !user.getUserName().isEmpty()) {
            // 判斷該用戶是否存在
            LyUser lyUser = userService.checkUser(user.getUserName());
            if (null != lyUser) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10013);
            }
            user.setRegistrationTime(new Date());
            String nameShorthand = ChineseToEnUtil.getPinYinHeadChar(user.getNickName());
            user.setNameShorthand(nameShorthand);
            int i = userService.insetUser(user);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
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
    @PutMapping("/updateUser")
    @ApiOperation(value = "修改客戶信息")
    public ResponseObj updateUser(@RequestBody LyUser user) {
        if (null != user.getId()) {
            int i = userService.updateUser(user);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 刪除用戶
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "删除客戶")
    public ResponseObj deleteUser(@RequestParam Integer userId) {
        if (null != userId) {
            int i = userService.deleteUser(userId);
            if (i < 0) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10002);
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 根據關鍵字查詢用戶
     * 匹配id、客户账号、昵称、手机号码、賬號拼音簡寫
     *
     * @param
     * @return user
     */
    @GetMapping("/getUsers")
    @ApiOperation(value = "根據關鍵字查詢客戶")
    @ApiImplicitParam(name = "keywords", value = "關鍵字", paramType = "query", dataType = "String")
    public ResponseObj getUsers(@RequestParam(required = false) String keywords) {
        PageInfo<LyUser> pageInfo = null;
        try {
            pageInfo = new PageInfo(userService.getUserByUserKeywords(keywords));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(pageInfo);
    }

}

