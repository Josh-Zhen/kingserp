package com.moonlit.kingserp.admin.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.admin.GoodsKind;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * 商品種類表 Mapper 接口
 *
 * @author Joshua
 * @since 2020-04-19
 */
public interface GoodsKindMapper extends MyMapper<GoodsKind> {

    /**
     * 根據關鍵字查詢商品種類
     *
     * @param keywords
     * @return ArrayList<GoodsKind>
     */
    @Select({"<script>",
            "SELECT gk.*,(SELECT COUNT(*) FROM `goods_spu` gs WHERE gs.kind_id = gk.id ) AS goodsSpuSum FROM`goods_kind` gk",
            "<if test='keywords != null and keywords != &quot;&quot;'>",
            "WHERE CONCAT(gk.id, gk.name, gk.name_shorthand) LIKE '%' #{keywords} '%'",
            "</if>",
            "ORDER BY gk.parent_id, gk.sep",
            "</script>"})
    ArrayList<GoodsKind> selectGoodsKindByKeywords(String keywords);

    /**
     * 根據Id查詢商品種類
     *
     * @param goodsKindId
     * @return
     */
    @Select("SELECT gk.*, COUNT( gs.id ) AS goodsSpuSum FROM `goods_kind` gk LEFT JOIN `goods_spu` gs ON gs.kind_id = gk.id WHERE gk.id = #{goodsKindId}")
    GoodsKind getGoodsKindById(Integer goodsKindId);
}
