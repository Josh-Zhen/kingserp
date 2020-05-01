package com.moonlit.kingserp.admin.service;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.entity.admin.GoodsKind;

import java.util.ArrayList;

/**
 * 商品種類表 服务类
 *
 * @author Joshua
 * @since 2020-04-19
 */
public interface GoodsKindService {

    /**
     * 添加商品種類
     *
     * @param goodsKind
     * @return 0/1
     */
    int addGoodsKind(GoodsKind goodsKind);

    /**
     * 刪除商品種類
     *
     * @param goodsKindId
     * @return 0/1
     */
    int delectByGoodsKindId(Integer goodsKindId);

    /**
     * 修改商品種類
     *
     * @param goodsKind
     * @return
     */
    int update(GoodsKind goodsKind);

    /**
     * 根據關鍵字查詢商品種類
     *
     * @param currentPage
     * @param pageSize
     * @param keywords
     * @return
     */
    PageInfo<GoodsKind> selectGoodsKind(Integer currentPage, Integer pageSize, String keywords);

    /**
     * 根據Id查詢商品種類
     *
     * @param goodsKindId
     * @return
     */
    GoodsKind getGoodsKindById(Integer goodsKindId);

    /**
     * 查詢商品種類
     *
     * @return
     */
    ArrayList<GoodsKind> selectGoodsKinds();
}
