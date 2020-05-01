package com.moonlit.kingserp.admin.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.admin.GoodsLog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 系统日志 Mapper 接口
 * @Author: Joshua
 * @CreateDate: 2020-02-21 23:43
 * @Version 1.0
 */
public interface GoodsLogMapper extends MyMapper<GoodsLog> {

    /**
     * 關鍵字查詢日志
     *
     * @param keywords
     * @return
     */
    @Select({"<script>",
            "SELECT sl.*, su.user_name AS userName FROM sys_log sl LEFT JOIN sys_user su ON su.id = sl.u_id",
            "<if test='keywords != null and keywords != &quot;&quot;'>",
            "WHERE CONCAT( sl.id, sl.u_id, sl.method ) LIKE '%' #{keywords} '%'",
            "</if>",
            "ORDER BY sl.create_date DESC",
            "</script>"})
    List<GoodsLog> selectLogsByKeywords(String keywords);
}
