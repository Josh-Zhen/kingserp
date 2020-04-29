package com.moonlit.kingserp.admin.controller.goods;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.service.GoodsKindService;
import com.moonlit.kingserp.admin.service.LogService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.GoodsKind;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

/**
 * 商品種類
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Slf4j
@RestController
@RequestMapping("/goodsKind")
@Api(value = "商品種類", tags = {"商品種類"})
public class GoodsKindController {

    @Autowired
    private GoodsKindService goodsKindService;
    @Autowired
    private LogService logService;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 添加商品種類
     *
     * @param goodsKind
     * @return
     */
    @NeedAuth
    @PostMapping("/addGoodsKind")
    @ApiOperation("添加商品種類")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj addGoodsKind(@RequestBody GoodsKind goodsKind) {
        //檢查商品種類是否存在
        if (null != goodsKindService.checkaGoodsKind(goodsKind.getName())) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER10013);
        }
        if (0 > goodsKindService.addGoodsKind(goodsKind)) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("addGoodsKind", "添加一個名：" + goodsKind.getName() + "的商品種類"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 刪除一個商品種類
     *
     * @param goodsKindId
     * @return
     */
    @NeedAuth
    @DeleteMapping("/deldectGoodsKind")
    @ApiOperation("刪除一個商品種類")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "goodsKindId", value = "商品種類Id", paramType = "query", dataType = "Integer")
    })
    public ResponseObj deldectGoodsKind(@RequestParam Integer goodsKindId) {
        if (null == goodsKindId) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
        }
        if (0 > goodsKindService.delectByGoodsKindId(goodsKindId)) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20502);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("deldectGoodsKind", "刪除Id為：" + goodsKindId + "的商品種類"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 修改商品種類
     *
     * @return
     */
    @NeedAuth
    @PutMapping("/updateGoodsKind")
    @ApiOperation("修改商品種類")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj updateGoodsKind(@RequestBody GoodsKind goodsKind) {
        if (null != goodsKind.getId()) {
            if (null != goodsKindService.checkaGoodsKind(goodsKind.getName())) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10013);
            }
            if (0 > goodsKindService.update(goodsKind)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateGoodsKind", "修改商品種類Id：" + goodsKind.getId() + "的商品種類信息"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 修改商品種類狀態
     *
     * @return
     */
    @NeedAuth
    @DeleteMapping("/deldectGoodsKind")
    @ApiOperation("修改商品種類狀態")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "goodsKindId", value = "商品種類Id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "當前狀態", paramType = "query", dataType = "Integer")
    })
    public ResponseObj updateGoodsKindStatus(@RequestParam Integer goodsKindId, @RequestParam Integer status) {
        if (null != goodsKindId) {
            GoodsKind goodsKind = new GoodsKind();
            status = Math.abs(status - 1);
            goodsKind.setId(goodsKindId);
            goodsKind.setStatus(status);
            if (0 > goodsKindService.update(goodsKind)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
            }
        } else {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        threadPoolTaskExecutor.execute(() -> logService.addLog("updateGoodsKindStatus", "修改商品種類Id：" + goodsKindId + "的狀態"));
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 根據關鍵字查詢商品種類(匹配id、種類名称、助記碼)
     *
     * @return
     */
    @NeedAuth
    @GetMapping("/selectGoodsKind")
    @ApiOperation("根據關鍵字查詢商品種類")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "關鍵字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseObj selectGoodsKind(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                       @RequestParam(required = false) String keywords) {
        PageInfo<GoodsKind> goodsKinds = null;
        try {
            goodsKinds = goodsKindService.selectGoodsKind(currentPage, pageSize, keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse(goodsKinds);
    }

}

