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

    /**
     * 更新
     *
     * @param wxMemberCard 微信會員卡實體
     * @return
     */
    Integer updata(WxMemberCard wxMemberCard);

    /**
     * 根據商戶id查詢商戶微信會員卡模板
     *
     * @param wxMemberCard 商戶id
     * @return 商戶實體
     */
    WxMemberCard select(WxMemberCard wxMemberCard);
}
