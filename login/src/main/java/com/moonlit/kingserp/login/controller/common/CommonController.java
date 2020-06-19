package com.moonlit.kingserp.login.controller.common;

import com.moonlit.kingserp.common.response.ResponseObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 公共接口
 * @Author: Joshua
 * @CreateDate: 2020-06-19 10:04
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/createController")
@Api(value = "公共接口", tags = {"公共接口"})
public class CommonController {

    /**
     * 微信圖片上傳接口
     *
     * @param file
     * @return
     */
    @PostMapping("/WxUpdataImg")
    @ApiOperation("微信圖片上傳接口")
    public ResponseObj WxUpdataImg(@RequestParam(value = "file") MultipartFile file) {


        return ResponseObj.createSuccessResponse();
    }
}
