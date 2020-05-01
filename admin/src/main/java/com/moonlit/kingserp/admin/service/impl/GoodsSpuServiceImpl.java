package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.mapper.GoodsSkuMapper;
import com.moonlit.kingserp.admin.mapper.GoodsSpuMapper;
import com.moonlit.kingserp.admin.service.GoodsSpuService;
import com.moonlit.kingserp.common.exception.EpException;
import com.moonlit.kingserp.common.util.ChineseToEnUtil;
import com.moonlit.kingserp.entity.admin.GoodsSku;
import com.moonlit.kingserp.entity.admin.GoodsSpu;
import com.moonlit.kingserp.entity.admin.dto.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 商品Spu表 服务实现类
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Slf4j
@Service
public class GoodsSpuServiceImpl implements GoodsSpuService {

    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    /**
     * 添加商品Spu
     *
     * @param goodsSpu
     * @return
     */
    @Override
    public int addGoodsSpu(GoodsSpu goodsSpu) {
        int i = 0;
        try {
            Date date = new Date();
            goodsSpu.setCreateTime(date);
            goodsSpu.setUpdateTime(date);
            goodsSpu.setNameShorthand(ChineseToEnUtil.getPinYinHeadChar(goodsSpu.getGoodsName()));
            i = goodsSpuMapper.insertSelective(goodsSpu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGoods(Goods goods) {
        int i = 0;
        try {
            GoodsSpu goodsSpu = goods.getGoodsSpu();
            List<GoodsSku> goodsSkus = goods.getGoodsSkus();
            Date date = new Date();

            //Spu
            goodsSpu.setGoodsName(goodsSpu.getGoodsName().trim());
            goodsSpu.setCreateTime(date);
            goodsSpu.setUpdateTime(date);
            goodsSpu.setNameShorthand(ChineseToEnUtil.getPinYinHeadChar(goodsSpu.getGoodsName()));

            //計算庫存
            Integer count = 0;
            for (GoodsSku skus : goodsSkus) {
                if (null != skus.getNum()) {
                    count += skus.getNum();
                }
            }
            goodsSpu.setNum(count);

            i = goodsSpuMapper.insertSelective(goodsSpu);

            //Sku
            Integer spuId = goodsSpuMapper.getGoodsSpuByCode(goodsSpu.getCode()).getId();
            for (GoodsSku skus : goodsSkus) {
                skus.setSpuId(spuId);
                skus.setCreateTime(date);
                skus.setUpdateTime(date);
                i = goodsSkuMapper.insertSelective(skus);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加商品失敗,{}", e.getMessage());
            throw new EpException("添加失败,请正确填写,错误原因:" + e.getMessage(), 300);
        }
        return i;
    }


}
