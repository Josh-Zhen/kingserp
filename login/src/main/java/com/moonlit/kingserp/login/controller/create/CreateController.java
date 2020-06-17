package com.moonlit.kingserp.login.controller.create;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.MemberAlCard;
import com.moonlit.kingserp.login.common.CrateUtil;
import com.moonlit.kingserp.login.service.CreateSercvice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.*;

/**
 * 会员卡
 *
 * @author Joshua
 */
@Slf4j
@RestController
@RequestMapping("/createController")
@Api(value = "会员卡", tags = {"会员卡"})
public class CreateController {

    @Autowired
    private CrateUtil crateUtil;
    @Autowired
    private CreateSercvice createSercvice;

    /**
     * 添加商户会员卡模板
     */
    @PostMapping("/addCeateModel")
    @ApiOperation("添加商户会员卡模板")
    public ResponseObj addCeateModel(@RequestBody MemberAlCard card) {

        String sourceAppId = "dasd";

        String str = "{\n \"titl\":[\"Google\", \"Runoob\", \"Taobao\" ],\n" +
                "\"url\":[ \"Google.html\", \"Runoob.html\", \"Taobao.html\" ]\n }";
        JSONObject jsonObject = JSONObject.parseObject(str);
//        JSONObject jsonObject = JSONObject.parseObject(card.getCustomizeArr());
        // json轉list
        ArrayList<String> list = (ArrayList<String>) JSONObject.parseArray(jsonObject.getJSONArray("titl").toJSONString(), String.class);
        ArrayList<String> list2 = (ArrayList<String>) JSONObject.parseArray(jsonObject.getJSONArray("url").toJSONString(), String.class);
        HashMap<String, ArrayList<String>> customizeArrs = new HashMap<>(2);
        customizeArrs.put("titl", list);
        customizeArrs.put("url", list2);

        try {
            crateUtil.crateModel(card.getTitle(), card.getLogo(), card.getImg(), customizeArrs, sourceAppId);
        } catch (AlipayApiException e) {
            return ResponseObj.createErrResponse("調用失敗");
        }
        card.setCreateTime(new Date());

        createSercvice.addCreate(card);

        return ResponseObj.createSuccessResponse();
    }


    /**
     * 查詢會員卡
     *
     * @return
     */
    @GetMapping("/SelectCreate")
    @ApiOperation("查詢會員卡模板")
    public ResponseObj selectCreate(@RequestParam Integer merchantid) {
        MemberAlCard card = createSercvice.slecetCreate(merchantid);
        return ResponseObj.createSuccessResponse(card);
    }

    /**
     * 更新會員卡信息
     *
     * @param card
     * @return
     */
    @PutMapping("/updataCreate")
    @ApiOperation("更新會員卡模板信息")
    public ResponseObj updataCreate(@RequestBody MemberAlCard card) {

        String sourceAppId = "dasd";

        String str = "{\n \"titl\":[\"Google\", \"Runoob\", \"Taobao\" ],\n" +
                "\"url\":[ \"Google.html\", \"Runoob.html\", \"Taobao.html\" ]\n }";
        JSONObject jsonObject = JSONObject.parseObject(str);
//        JSONObject jsonObject = JSONObject.parseObject(card.getCustomizeArr());
        // json轉list
        ArrayList<String> list = (ArrayList<String>) JSONObject.parseArray(jsonObject.getJSONArray("titl").toJSONString(), String.class);
        ArrayList<String> list2 = (ArrayList<String>) JSONObject.parseArray(jsonObject.getJSONArray("url").toJSONString(), String.class);
        HashMap<String, ArrayList<String>> customizeArrs = new HashMap<>(2);
        customizeArrs.put("titl", list);
        customizeArrs.put("url", list2);

        try {
            crateUtil.updataCrate(card.getTitle(), card.getLogo(), card.getImg(), customizeArrs, sourceAppId, card.getTemplateId());
        } catch (AlipayApiException e) {
            return ResponseObj.createErrResponse("調用失敗");
        }

        if (0 > createSercvice.updata(card)) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        return ResponseObj.createSuccessResponse();
    }

    @GetMapping("/setCrad")
    @ApiOperation("會員卡開卡")
    public ResponseObj setCrad(@RequestParam String templateId) {
        try {
            crateUtil.SetCard(templateId);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return ResponseObj.createErrResponse("開卡失敗");
        }
        return ResponseObj.createSuccessResponse();
    }
}
