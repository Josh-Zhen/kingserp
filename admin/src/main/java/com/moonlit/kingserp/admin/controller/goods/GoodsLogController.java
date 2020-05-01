package com.moonlit.kingserp.admin.controller.goods;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.service.GoodsLogService;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.GoodsLog;
import com.moonlit.kingserp.entity.admin.SysLog;
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

/**
 * @Description: 商品日志記錄
 * @Author: Joshua
 * @CreateDate: 2020-05-01 15:31
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/goodsLog")
@Api(value = "商品日志記錄", tags = {"商品日志記錄"})
public class GoodsLogController {

    @Autowired
    private GoodsLogService goodsLogService;

    /**
     * 查詢操作日志
     *
     * @return
     */
    @NeedAuth
    @GetMapping("/selectLogs")
    @ApiOperation("查詢操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "關鍵字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj selectLogs(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                  @RequestParam(required = false) String keywords) {
        PageInfo<GoodsLog> logs = new PageInfo<>();
        try {
            logs = goodsLogService.selectLog(currentPage, pageSize, keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(logs);
    }
}
