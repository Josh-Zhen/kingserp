package com.moonlit.kingserp.admin.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.admin.GoodsSpu;
import org.apache.ibatis.annotations.Select;

/**
 * 商品Spu表 Mapper 接口
 *
 * @author Joshua
 * @since 2020-04-19
 */
public interface GoodsSpuMapper extends MyMapper<GoodsSpu> {

    /**
     * 根據Code查詢Spu
     *
     * @param code
     * @return
     */
    @Select("")
    GoodsSpu getGoodsSpuByCode(String code);
}
