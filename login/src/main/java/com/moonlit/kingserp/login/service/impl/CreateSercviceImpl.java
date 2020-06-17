package com.moonlit.kingserp.login.service.impl;

import com.moonlit.kingserp.entity.login.MemberAlCard;
import com.moonlit.kingserp.login.mapper.CreateMapper;
import com.moonlit.kingserp.login.service.CreateSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-06-17 10:24
 * @Version 1.0
 */
@Service
public class CreateSercviceImpl implements CreateSercvice {

    @Autowired
    private CreateMapper createMapper;

    /**
     * 新增會員卡模板
     *
     * @param card
     */
    @Override
    public Integer addCreate(MemberAlCard card) {
        Integer i = 0;
        createMapper.insertSelective(card);
        return i;
    }

    /**
     * 查詢會員卡模板信息
     *
     * @param merchantid
     * @return
     */
    @Override
    public MemberAlCard slecetCreate(Integer merchantid) {
        MemberAlCard card = new MemberAlCard();
        card.setId(merchantid);
        return createMapper.selectOne(card);
    }

    /**
     * 更新會員卡模板信息
     *
     * @param card
     * @return
     */
    @Override
    public Integer updata(MemberAlCard card) {
        int i = 0;
        try {
            i = createMapper.updateByPrimaryKeySelective(card);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
