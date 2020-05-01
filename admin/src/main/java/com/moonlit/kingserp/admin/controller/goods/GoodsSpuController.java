package com.moonlit.kingserp.admin.controller.goods;

import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.service.GoodsSkuService;
import com.moonlit.kingserp.admin.service.GoodsSpuService;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.admin.dto.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品Spu
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Slf4j
@RestController
@RequestMapping("/goodsSpu")
@Api(value = "商品Spu", tags = {"商品Spu"})
public class GoodsSpuController {

    @Autowired
    private GoodsSpuService goodsSpuService;
    @Autowired
    private GoodsSkuService goodsSkuService;

    /**
     * 添加商品
     *
     * @return
     */
    @NeedAuth
    @PostMapping("/addGoods")
    @ApiOperation("添加商品")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj addGoods(@RequestBody Goods goods) {
        if (null != goods.getGoodsSpu()) {
            if (0 > goodsSpuService.addGoods(goods)) {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
            }

        }

        return ResponseObj.createSuccessResponse();
    }
}

