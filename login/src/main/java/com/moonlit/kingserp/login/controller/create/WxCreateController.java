package com.moonlit.kingserp.login.controller.create;

import com.moonlit.kingserp.common.response.ResponseObj;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 微信会员卡
 * @Author: Joshua
 * @CreateDate: 2020-06-19 11:25
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/wxCreateController")
@Api(value = "微信会员卡", tags = {"微信会员卡"})
public class WxCreateController {

    /**
     * @return
     */
    public ResponseObj addCeateModel() {

        return ResponseObj.createSuccessResponse();
    }



}
