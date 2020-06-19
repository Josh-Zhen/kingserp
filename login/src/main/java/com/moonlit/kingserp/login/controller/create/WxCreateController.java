package com.moonlit.kingserp.login.controller.create;

import com.alibaba.fastjson.JSON;
import com.moonlit.kingserp.common.response.ResponseObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpCardService;
import me.chanjar.weixin.mp.api.WxMpMemberCardService;
import me.chanjar.weixin.mp.bean.card.MemberCard;
import me.chanjar.weixin.mp.bean.card.MemberCardCreateRequest;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateRequest;
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

    /**
     * 創建會員卡模板
     *
     * @return
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


        // 轉String
        String memberCardToString = JSON.toJSONString(createRequest);

        //創建會員卡
        WxMpCardCreateResult openCard = wxMpMemberCardService.createMemberCard(memberCardToString);
        if (openCard.getErrmsg()=="ok"){
            openCard.getCardId();

        }


        return ResponseObj.createSuccessResponse();
    }


}
