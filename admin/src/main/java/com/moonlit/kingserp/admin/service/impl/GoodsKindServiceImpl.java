package com.moonlit.kingserp.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.mapper.GoodsKindMapper;
import com.moonlit.kingserp.admin.service.GoodsKindService;
import com.moonlit.kingserp.common.util.ChineseToEnUtil;
import com.moonlit.kingserp.entity.admin.GoodsKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * 商品種類表 服务实现类
 *
 * @author Joshua
 * @since 2020-04-19
 */
@Service
public class GoodsKindServiceImpl implements GoodsKindService {

    @Autowired
    private GoodsKindMapper goodsKindMapper;

    /**
     * 添加商品種類
     *
     * @param goodsKind
     * @return
     */
    @Override
    public int addGoodsKind(GoodsKind goodsKind) {
        int i = 0;
        try {
            goodsKind.setCreateTime(new Date());
            goodsKind.setUpdateTime(new Date());
            goodsKind.setNameShorthand(ChineseToEnUtil.getPinYinHeadChar(goodsKind.getName()));
            i = goodsKindMapper.insertSelective(goodsKind);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 刪除商品種類
     *
     * @param goodsKindId
     * @return
     */
    @Override
    public int delectByGoodsKindId(Integer goodsKindId) {
        int i = 0;
        GoodsKind goodsKind = new GoodsKind();
        goodsKind.setId(goodsKindId);
        try {
            i = goodsKindMapper.delete(goodsKind);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 修改商品種類
     *
     * @param goodsKind
     * @return
     */
    @Override
    public int update(GoodsKind goodsKind) {
        int i = 0;
        try {
            if (null != goodsKind.getName()) {
                goodsKind.setNameShorthand(ChineseToEnUtil.getPinYinHeadChar(goodsKind.getName()));
            }
            goodsKind.setUpdateTime(new Date());
            i = goodsKindMapper.updateByPrimaryKeySelective(goodsKind);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根據關鍵字查詢商品種類
     *
     * @param currentPage
     * @param pageSize
     * @param keywords
     * @return
     */
    @Override
    public PageInfo<GoodsKind> selectGoodsKind(Integer currentPage, Integer pageSize, String keywords) {
        PageInfo pageInfo = null;
        PageHelper.startPage(currentPage, pageSize);
        try {
            pageInfo = new PageInfo(goodsKindMapper.selectGoodsKindByKeywords(keywords));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    /**
     * 根據Id查詢商品種類
     *
     * @param goodsKindId
     * @return GoodsKind
     */
    @Override
    public GoodsKind getGoodsKindById(Integer goodsKindId) {
        return goodsKindMapper.getGoodsKindById(goodsKindId);
    }

    /**
     * 查詢商品種類
     *
     * @return
     */
    @Override
    public ArrayList<GoodsKind> selectGoodsKinds() {
        ArrayList<GoodsKind> goodsKinds = new ArrayList<>();
        try {
            goodsKinds = goodsKindMapper.selectAlls();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodsKinds;
    }
}
