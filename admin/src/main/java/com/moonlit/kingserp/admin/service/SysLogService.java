package com.moonlit.kingserp.admin.service;

import com.github.pagehelper.PageInfo;
import com.moonlit.kingserp.entity.admin.SysLog;

/**
 * @Description: 日志記錄
 * @Author: Joshua
 * @CreateDate: 2020-02-21 23:28
 * @Version 1.0
 */
public interface SysLogService {

    /**
     * 添加日志
     *
     * @param method 请求方法
     * @param data   内容
     */
    void addLog(String method, String data);

    /**
     * 查詢日志
     *
     * @param currentPage
     * @param pageSize
     * @param keywords
     * @return
     */
    PageInfo<SysLog> selectLog(Integer currentPage, Integer pageSize, String keywords);

}
