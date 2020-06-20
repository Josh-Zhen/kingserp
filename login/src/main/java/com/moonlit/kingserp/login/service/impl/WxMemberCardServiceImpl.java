package com.moonlit.kingserp.login.service.impl;

import com.moonlit.kingserp.entity.login.WxMemberCard;
import com.moonlit.kingserp.login.mapper.WxMemberCardMapper;
import com.moonlit.kingserp.login.service.wx.WxMemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 微信会员卡 服务实现类
 * @Author: Joshua
 * @CreateDate: 2020-06-20 10:56
 * @Version 1.0
 */
@Service
public class WxMemberCardServiceImpl implements WxMemberCardService {

    @Autowired
    private WxMemberCardMapper memberCardMapper;

    /**
     * 插入一條數據
     *
     * @param wxMemberCard 微信會員卡實體
     * @return
     */
    @Override
    public Integer insetr(WxMemberCard wxMemberCard) {
        int i = 0;
        try {
            i = memberCardMapper.insert(wxMemberCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
