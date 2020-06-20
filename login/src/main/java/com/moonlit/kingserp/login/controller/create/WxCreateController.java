package com.moonlit.kingserp.login.controller.create;

import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.exception.EpException;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.WxMemberCard;
import com.moonlit.kingserp.login.service.wx.WxMemberCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpCardService;
import me.chanjar.weixin.mp.api.WxMpMemberCardService;
import me.chanjar.weixin.mp.bean.card.MemberCard;
import me.chanjar.weixin.mp.bean.card.MemberCardCreateRequest;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import me.chanjar.weixin.mp.bean.card.WxMpCardQrcodeCreateResult;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardCreateMessage;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUpdateMessage;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    private WxMpCardService wxMpCardService;
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

    /**
     * 創建二維碼
     * 需要等待會員卡過審后才可調用
     *
     * @param id         微信會員卡表主鍵id
     * @param merchantId 商戶表主鍵id
     * @param cardId     會員卡id
     * @return
     */
    @GetMapping("/createQrCode")
    @ApiOperation("/創建二維碼，需要等待會員卡過審后才可調用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "微信會員卡表主鍵id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "merchantId", value = "商戶表主鍵id", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "cardId", value = "會員卡id", paramType = "query", dataType = "String"),
    })
    public ResponseObj createQrCode(@RequestParam Integer id, @RequestParam Integer merchantId, @RequestParam String cardId) {
        WxMemberCard wxMemberCard = new WxMemberCard();

        // outer_id字段升级版本，字符串类型，用户首次领卡时，会通过 领取事件推送 给商户； 对于会员卡的二维码，用户每次扫码打开会员卡后点击任何url，会将该值拼入url中，方便开发者定位扫码来源
        // 可自定義參數(會員卡表主鍵id + 商戶主鍵id)
        String outerStr = "create" + id.toString() + "merchantId" + merchantId.toString();
        // 獲取會員卡二維碼
        try {
            WxMpCardQrcodeCreateResult qrcodeCard = wxMpCardService.createQrcodeCard(cardId, outerStr);
            if (0 == qrcodeCard.getErrcode()) {
                wxMemberCard.setShowQrcodeUrl(qrcodeCard.getShowQrcodeUrl());
                if (0 > wxMemberCardService.updata(wxMemberCard)) {
                    return ResponseObj.createErrResponse(ErrerMsg.ERRER20501);
                }
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10018);
            }
        } catch (WxErrorException e) {
            // TODO 未作請求異常處理
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 查詢微信會員卡模板
     *
     * @param merchantId 商戶id
     * @return
     */
    @GetMapping("/selectCeateModel")
    @ApiOperation("/查詢微信會員卡模板")
    @ApiImplicitParam(name = "merchantId", value = "商戶id", paramType = "query", dataType = "Integer")
    public ResponseObj selectCeateModel(@RequestParam Integer merchantId) {
        WxMemberCard wxMemberCard = new WxMemberCard();
        wxMemberCard.setMerchantId(merchantId);
        WxMemberCard memberCard = wxMemberCardService.select(wxMemberCard);
        return ResponseObj.createSuccessResponse(memberCard);
    }

    /**
     * 更新微信會員卡模板
     *
     * @return
     */
    @PutMapping("/updataCeateModel")
    @ApiOperation("更新微信會員卡模板")
    public ResponseObj updataCeateModel(@RequestBody WxMpMemberCardUpdateMessage updateUserMessage) {
        WxMemberCard wxMemberCard = new WxMemberCard();
        try {
            WxMpMemberCardUpdateResult memberCardUpdateResult = wxMpMemberCardService.updateUserMemberCard(updateUserMessage);
            if (0 == Integer.parseInt(memberCardUpdateResult.getErrorCode())) {

                wxMemberCard.setBackgroundPicUrl(updateUserMessage.getBackgroundPicUrl());
            } else {
                return ResponseObj.createErrResponse(ErrerMsg.ERRER10018);
            }
        } catch (WxErrorException e) {
            // TODO 未作請求異常處理
            e.printStackTrace();
        }
        return ResponseObj.createSuccessResponse();
    }
}
