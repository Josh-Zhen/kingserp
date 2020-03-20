package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;
import com.moonlit.kingserp.admin.mapper.SysLogMapper;
import com.moonlit.kingserp.admin.service.LogService;
import com.moonlit.kingserp.entity.admin.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Description:
 * @Author: Joshua
 * @CreateDate: 2020-02-21 23:36
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService {

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
    public ArrayList<SysLog> selectLog() {
        return null;
    }
}
