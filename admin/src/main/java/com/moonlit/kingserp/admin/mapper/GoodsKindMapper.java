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
     * @return
     */
    @Select({"<script>",
            "select * from `goods_kind`",
            "<if test='keywords != null and keywords != &quot;&quot;'>",
            "WHERE CONCAT(id,name,name_shorthand) LIKE '%' #{keywords} '%'",
            "</if>",
            "ORDER BY create_time DESC",
            "</script>"})
    ArrayList<GoodsKind> selectGoodsKindByKeywords(String keywords);
}
