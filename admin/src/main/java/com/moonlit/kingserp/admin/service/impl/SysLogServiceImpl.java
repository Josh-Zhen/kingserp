package com.moonlit.kingserp.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.mapper.SysLogMapper;
import com.moonlit.kingserp.admin.service.SysLogService;
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
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogMapper sysLogMapper;

    /**
     * 添加日志
     *
     * @param method 请求方法
     * @param data   内容
     */
    @Override
    public void addLog(String method, String data) {
        try {
            SysLog sysLog = new SysLog();
            sysLog.setUId(ShiroUtils.getUserInfo().getId());
            sysLog.setCreateDate(new Date());
            sysLog.setMethod(method);
            sysLog.setData(data);
            sysLogMapper.insert(sysLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查詢日志
     *
     * @return
     */
    @Override
    public PageInfo<SysLog> selectLog(Integer currentPage, Integer pageSize, String keywords) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(currentPage, pageSize);
            pageInfo = new PageInfo(sysLogMapper.selectLogsByKeywords(keywords));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }
}
