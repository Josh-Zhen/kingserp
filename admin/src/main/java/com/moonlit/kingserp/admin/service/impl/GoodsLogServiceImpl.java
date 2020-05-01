package com.moonlit.kingserp.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.mapper.GoodsLogMapper;
import com.moonlit.kingserp.admin.service.GoodsLogService;
import com.moonlit.kingserp.entity.admin.GoodsLog;
import com.moonlit.kingserp.entity.admin.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-02-21 23:36
 * @Version 1.0
 */
@Service
public class GoodsLogServiceImpl implements GoodsLogService {

    @Autowired
    GoodsLogMapper goodsLogMapper;

    /**
     * 添加日志
     *
     * @param method 请求方法
     * @param data   内容
     */
    @Override
    public void addLog(String method, String data) {
        try {
            GoodsLog goodsLog = new GoodsLog();
            goodsLog.setUId(ShiroUtils.getUserInfo().getId());
            goodsLog.setCreateDate(new Date());
            goodsLog.setMethod(method);
            goodsLog.setData(data);
            goodsLogMapper.insert(goodsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查詢日志
     *
     * @param currentPage
     * @param pageSize
     * @param keywords
     * @return
     */
    @Override
    public PageInfo<GoodsLog> selectLog(Integer currentPage, Integer pageSize, String keywords) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(currentPage, pageSize);
            pageInfo = new PageInfo(goodsLogMapper.selectLogsByKeywords(keywords));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
