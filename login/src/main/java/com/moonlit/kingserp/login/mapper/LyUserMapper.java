package com.moonlit.kingserp.login.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.login.LyUser;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * 客戶信息 Mapper 接口
 *
 * @author Joshua
 * @since 2020-02-04
 */
public interface LyUserMapper extends MyMapper<LyUser> {
    /**
     * 多條件模糊查詢
     *
     * @param keywords
     * @return
     */
    @Select({"<script>",
            "select * from `ly_user` WHERE type = 0",
            "<if test='keywords != null and keywords != &quot;&quot;'>",
            "AND CONCAT(id,user_name,nick_name,phone,name_shorthand) LIKE '%' #{keywords} '%'",
            "</if>",
            "ORDER BY registration_time DESC",
            "</script>"})
    ArrayList<LyUser> selectByKeywords(String keywords);

}
