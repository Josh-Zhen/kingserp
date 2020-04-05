package com.moonlit.kingserp.login.service;

import com.moonlit.kingserp.entity.login.LyUser;

import java.util.ArrayList;

/**
 * 客戶信息 服务类
 *
 * @author Joshua
 * @since 2020-02-04
 */
public interface LyUserService {

    /**
     * 根據關鍵字查詢客戶
     *
     * @param keywords
     * @return
     */
    ArrayList<LyUser> getUserByUserKeywords(String keywords);

    /**
     * 根據客戶賬號查詢客戶是否已經存在
     *
     * @param userName
     * @return
     */
    LyUser checkUser(String userName);

    /**
     * 添加客戶
     *
     * @param user
     * @return
     */
    int insetUser(LyUser user);

    /**
     * 更新客戶信息
     *
     * @param user
     * @return
     */
    int updateUser(LyUser user);

    /**
     * 删除客戶
     *
     * @param userId
     * @return
     */
    int deleteUser(Integer userId);

}
