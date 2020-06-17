package com.moonlit.kingserp.login.controller.create;

import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.MemberAlCard;
import com.moonlit.kingserp.login.common.CrateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 会员卡
 */
@Slf4j
@RestController
@RequestMapping("/createController")
@Api(value = "会员卡", tags = {"会员卡"})
public class CreateController {

    @Autowired
    private CrateUtil crateUtil;

    /**
     * 添加商户会员卡模板
     */
    @PostMapping("/addCeateModel")
    @ApiOperation("添加商户会员卡模板")
    public ResponseObj addCeateModel(@RequestBody MemberAlCard card){
        card.getCustomizeArr();

        HashMap<String, ArrayList<String>> customizeArrs;


//        crateUtil.crateModel(card.getCardShowName(),card.getLogoId(),card.getBackgroundId(),customizeArrs);
        card.setCreateTime(new Date());

        return ResponseObj.createSuccessResponse();
    }
}
