package com.moonlit.kingserp.login.service.wx;

import com.moonlit.kingserp.entity.login.WxMemberCard;

/**
 * <p>
 * 微信会员卡 服务类
 * </p>
 *
 * @author Joshua
 * @since 2020-06-19
 */
public interface WxMemberCardService {

    /**
     * 插入一條數據
     *
     * @param wxMemberCard 微信會員卡實體
     * @return
     */
    Integer insetr(WxMemberCard wxMemberCard);
}
