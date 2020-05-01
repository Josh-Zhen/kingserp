package com.moonlit.kingserp.admin.service;

import com.moonlit.kingserp.entity.admin.GoodsSpu;
import com.moonlit.kingserp.entity.admin.dto.Goods;

/**
 * 商品Spu表 服务类
 *
 * @author Joshua
 * @since 2020-04-19
 */
public interface GoodsSpuService {

    /**
     * 添加商品Spu
     *
     * @param goodsSpu
     * @return
     */
    int addGoodsSpu(GoodsSpu goodsSpu);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    int addGoods(Goods goods);
}
