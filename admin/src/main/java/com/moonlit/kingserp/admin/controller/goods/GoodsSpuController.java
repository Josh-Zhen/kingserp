package com.moonlit.kingserp.admin.controller.goods;

import com.moonlit.kingserp.admin.common.annotation.NeedAuth;
import com.moonlit.kingserp.admin.service.GoodsSkuService;
import com.moonlit.kingserp.admin.service.GoodsSpuService;
import com.moonlit.kingserp.common.response.ResponseObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/addGoods")
    @ApiOperation("添加商品")
    @ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "String", paramType = "header")
    public ResponseObj addGoods() {

        return ResponseObj.createSuccessResponse();
    }
}

