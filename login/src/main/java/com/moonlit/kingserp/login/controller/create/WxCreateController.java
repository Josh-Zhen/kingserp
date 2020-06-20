package com.moonlit.kingserp.login.controller.create;

import com.alibaba.fastjson.JSON;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.exception.EpException;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.WxMemberCard;
import com.moonlit.kingserp.login.service.wx.WxMemberCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMemberCardService;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.bean.card.MemberCard;
import me.chanjar.weixin.mp.bean.card.MemberCardCreateRequest;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardCreateMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
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
     * <p>
     * Ps：當創建成功後，微信會審核，需等待3個工作日
     *
     * @param memberCard 會員卡實體
     * @return
     */
    @PostMapping("/addCeateModel")
    @ApiOperation("創建會員卡模板")
    public ResponseObj addCeateModel(@RequestBody MemberCard memberCard) {

        Integer merchantId = 1;

        // 設置自動開卡
        memberCard.setWxActivate(true);
        // 显示积分、支持储值
        memberCard.setSupplyBonus(true);
        memberCard.setSupplyBalance(true);
        // 設置积分外链、余额外链(接口地址需要動態帶用戶id)
        memberCard.setBonusUrl("");
        memberCard.setBalanceUrl("");

        // 封裝實體
        MemberCardCreateRequest createRequest = new MemberCardCreateRequest();
        createRequest.setMemberCard(memberCard);
//        String memberCardToString = JSON.toJSONString(createRequest);
        WxMpMemberCardCreateMessage wxMpMemberCardCreateMessage = new WxMpMemberCardCreateMessage();
        wxMpMemberCardCreateMessage.setCardCreateRequest(createRequest);

        //創建會員卡
        try {
//            WxMpCardCreateResult openCard = wxMpMemberCardService.createMemberCard(memberCardToString);
            WxMpCardCreateResult openCard = wxMpMemberCardService.createMemberCard(wxMpMemberCardCreateMessage);

            // 0為成功，其他皆爲錯誤
            if (0 == openCard.getErrcode()) {
                WxMemberCard wxMemberCard = new WxMemberCard();
                wxMemberCard.setMerchantId(merchantId);
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
                // TODO 默認保存3個 需要多個後續使用json實行該功能
                wxMemberCard.setCustomCells(memberCard.getCustomCell1().toString() + memberCard.getCustomCell2().toString() + memberCard.getCustomCell3().toString());
                wxMemberCard.setCardId(openCard.getCardId());
                // 存庫
                if (0 > wxMemberCardService.insetr(wxMemberCard)) {
                    //TODO 如果存庫失敗，需要提前保存已經設置的内容（待補充）
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
                }
            } else {
                //TODO 如果返回異常
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10018);
            }
        } catch (WxErrorException e) {
            //TODO 補異常處理
            throw new EpException("請求異常");
        }
        return ResponseObj.createSuccessResponse();
    }


}
