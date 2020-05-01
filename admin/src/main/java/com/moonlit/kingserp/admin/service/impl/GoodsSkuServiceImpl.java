package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.mapper.GoodsSkuMapper;
import com.moonlit.kingserp.admin.mapper.GoodsSpuMapper;
import com.moonlit.kingserp.admin.service.GoodsSkuService;
import com.moonlit.kingserp.entity.admin.GoodsSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品Sku表 服务实现类
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Service
public class GoodsSkuServiceImpl implements GoodsSkuService {

    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    /**
     * 添加商品Skus
     *
     * @param goodsSkus
     * @return
     */
    @Override
    public int addGoodsSkus(List<GoodsSku> goodsSkus) {

        return 0;
    }
}
