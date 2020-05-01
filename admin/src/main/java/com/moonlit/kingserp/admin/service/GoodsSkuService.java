package com.moonlit.kingserp.admin.service;

import com.moonlit.kingserp.entity.admin.GoodsSku;

import java.util.List;

/**
 * 商品Sku表 服务类
 *
 * @author Joshua
 * @since 2020-04-19
 */
public interface GoodsSkuService {

    /**
     * 添加商品Skus
     *
     * @param goodsSkus
     * @return
     */
    int addGoodsSkus(List<GoodsSku> goodsSkus);
}
