package com.moonlit.kingserp.login.controller.create;

import com.alibaba.fastjson.JSONObject;
import com.moonlit.kingserp.common.errer.ErrerMsg;
import com.moonlit.kingserp.common.response.ResponseObj;
import com.moonlit.kingserp.entity.login.MemberAlCard;
import com.moonlit.kingserp.login.service.impl.AliCrateServiceImpl;
import com.moonlit.kingserp.login.service.CreateSercvice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    private AliCrateServiceImpl aliCrateServiceImpl;
    @Autowired
    private CreateSercvice createSercvice;

    /**
     * 添加商户会员卡模板
     */
    @PostMapping("/addCeateModel")
    @ApiOperation("添加商户会员卡模板")
    public ResponseObj addCeateModel(@RequestBody MemberAlCard card) {
        String templateId;

        String sourceAppId = "dasd";
        String phome = "1237281391723913";

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

        templateId = aliCrateServiceImpl.crateModel(card.getTitle(), card.getLogo(), card.getImg(), customizeArrs, phome, sourceAppId);
        card.setCreateTime(new Date());
        if (0 > createSercvice.addCreate(card)) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20504);
        }
        return ResponseObj.createSuccessResponse(templateId);
    }


    /**
     * 查詢會員卡
     *
     * @return
     */
    @GetMapping("/SelectCreate")
    @ApiOperation("查詢會員卡模板")
    @ApiImplicitParam(name = "merchantid", value = "主鍵id", paramType = "query", dataType = "Integer")
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
        String templateId;

        String sourceAppId = "dasd";
        String phome = "1237281391723913";

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

        templateId = aliCrateServiceImpl.updataCrate(card.getTitle(), card.getLogo(), card.getImg(), customizeArrs, sourceAppId, phome, card.getTemplateId());

        if (0 > createSercvice.updata(card)) {
            return ResponseObj.createErrResponse(ErrerMsg.ERRER20503);
        }
        return ResponseObj.createSuccessResponse(templateId);
    }

    /**
     * 會員卡開卡
     *
     * @param templateId
     * @return
     */
    @GetMapping("/setCrad")
    @ApiOperation("會員卡開卡")
    @ApiImplicitParam(name = "templateId", value = "會員卡模板id", paramType = "query", dataType = "String")
    public ResponseObj setCrad(@RequestParam String templateId) {
        aliCrateServiceImpl.setCard(templateId);
        return ResponseObj.createSuccessResponse();
    }

    /**
     * 獲取會員卡鏈接
     *
     * @param templateId
     * @return
     */
    @GetMapping("/getCardQrcode")
    @ApiOperation("獲取會員卡鏈接")
    @ApiImplicitParam(name = "templateId", value = "會員卡模板id", paramType = "query", dataType = "String")
    public ResponseObj getCardQrcode(@RequestParam String templateId) {
        String cardQrcode;
        cardQrcode = aliCrateServiceImpl.cardQrcode(templateId);
        return ResponseObj.createSuccessResponse(cardQrcode);
    }


    /**
     * 獲取用戶提交的會員信息
     * 該接口僅提供測試
     *
     * @return
     */
    @GetMapping("/getQuery")
    @ApiOperation("獲取用戶提交的會員信息，該接口僅提供測試")
    public ResponseObj getQuery() {
        return ResponseObj.createSuccessResponse();
    }
}
