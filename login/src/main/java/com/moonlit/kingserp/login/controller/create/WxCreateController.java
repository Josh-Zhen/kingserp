package com.moonlit.kingserp.login.controller.create;

import com.alibaba.fastjson.JSON;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.WxMemberCard;
import com.moonlit.kingserp.login.service.wx.WxMemberCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMemberCardService;
import me.chanjar.weixin.mp.bean.card.MemberCard;
import me.chanjar.weixin.mp.bean.card.MemberCardCreateRequest;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 微信会员卡
 * @Author: Joshua
 * @CreateDate: 2020-06-19 11:25
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/wxCreateController")
@Api(value = "微信会员卡", tags = {"微信会员卡"})
public class WxCreateController {

    @Autowired
    private WxMpMemberCardService wxMpMemberCardService;
    @Autowired
    private WxMemberCardService wxMemberCardService;

    /**
     * 創建會員卡模板
     *
     * @param memberCard 會員卡實體
     * @return
     * @throws WxErrorException
     */
    @PostMapping("/addCeateModel")
    @ApiOperation("創建會員卡模板")
    public ResponseObj addCeateModel(@RequestBody MemberCard memberCard) throws WxErrorException {

        // 設置自動開卡
        memberCard.setWxActivate(true);
        // 显示积分、支持储值
        memberCard.setSupplyBonus(true);
        memberCard.setSupplyBalance(true);
        // 設置积分外链、余额外链(接口地址需要動態帶用戶id)
        memberCard.setBonusUrl("");
        memberCard.setBalanceUrl("");

        MemberCardCreateRequest createRequest = new MemberCardCreateRequest();
        createRequest.setMemberCard(memberCard);
        String memberCardToString = JSON.toJSONString(createRequest);

        //創建會員卡
        try {
            WxMpCardCreateResult openCard = wxMpMemberCardService.createMemberCard(memberCardToString);
            if ("ok".equals(openCard.getErrmsg())) {
                WxMemberCard wxMemberCard = new WxMemberCard();
                wxMemberCard.setBackgroundPicUrl(memberCard.getBackgroundPicUrl());
                wxMemberCard.setBrandName(memberCard.getBaseInfo().getBrandName());
                wxMemberCard.setLogoUrl(memberCard.getBaseInfo().getLogoUrl());
                wxMemberCard.setTitle(memberCard.getBaseInfo().getTitle());
                wxMemberCard.setCodeType(memberCard.getBaseInfo().getCodeType());
                wxMemberCard.setNotice(memberCard.getBaseInfo().getNotice());
                wxMemberCard.setDescription(memberCard.getBaseInfo().getDescription());
                wxMemberCard.setServicePhone(memberCard.getBaseInfo().getServicePhone());
                wxMemberCard.setPrerogative(memberCard.getPrerogative());
                wxMemberCard.setBusinessService(memberCard.getAdvancedInfo().getBusinessServiceList().toString());
                wxMemberCard.setTextImageList(memberCard.getAdvancedInfo().getTextImageList().toString());

                wxMemberCard.setCardId(openCard.getCardId());
                if (0 > wxMemberCardService.insetr(wxMemberCard)) {
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
                }
            } else {

            }
        } catch (WxErrorException e) {
            //TODO 補異常處理
            e.getError();
        }


        return ResponseObj.createSuccessResponse();
    }


}
