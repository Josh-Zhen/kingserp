package com.moonlit.kingserp.login.service;

import com.moonlit.kingserp.entity.login.MemberAlCard;

/**
 * @Description: 會員卡服務類
 * @Author: Joshua
 * @CreateDate: 2020-06-16 10:04
 * @Version 1.0
 */
public interface CreateSercvice {

    /**
     * 新增加會員卡模板
     */
    void addCreate(MemberAlCard card);

    /**
     * 查詢會員卡模板
     *
     * @param merchantid
     * @return
     */
    MemberAlCard slecetCreate(Integer merchantid);

    /**
     * 更新會員卡模板信息
     *
     * @param card
     * @return
     */
    Integer updata(MemberAlCard card);
}
