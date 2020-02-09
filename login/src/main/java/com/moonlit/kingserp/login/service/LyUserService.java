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
     * 根據關鍵字查詢用戶
     *
     * @param keywords
     * @return
     */
    ArrayList<LyUser> getUserByUserKeywords(String keywords);

    /**
     * 根據客戶賬號查詢用戶是否已經存在
     *
     * @param userName
     * @return
     */
    LyUser checkUser(String userName);

    /**
     * 新增客戶
     *
     * @param user
     * @return
     */
    int insetUser(LyUser user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    int updateUser(LyUser user);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    int deleteUser(Integer userId);

}
