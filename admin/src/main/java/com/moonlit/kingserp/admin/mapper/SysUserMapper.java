package com.moonlit.kingserp.admin.mapper;

import com.moonlit.kingserp.common.tkmapper.MyMapper;
import com.moonlit.kingserp.entity.admin.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * 系统用户 Mapper 接口
 *
 * @author Joshua
 * @since 2020-02-08
 */
public interface SysUserMapper extends MyMapper<SysUser> {

    /**
     * 模糊查詢管理者
     *
     * @param keywords
     * @return
     */
    @Select({"<script>",
            "select * from `sys_user` WHERE ",
            "<if test='keywords != null and keywords != &quot;&quot;'>",
            "CONCAT(id,user_name,mobile) LIKE '%' #{keywords} '%'",
            "</if>",
            "ORDER BY create_time DESC",
            "</script>"})
    ArrayList<SysUser> selectUserByUserKeywords(String keywords);
}
