package com.moonlit.kingserp.login.service.impl;

import com.moonlit.kingserp.entity.login.LyUser;
import com.moonlit.kingserp.login.mapper.LyUserMapper;
import com.moonlit.kingserp.login.service.LyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 客戶信息 服务实现类
 *
 * @author Joshua
 * @since 2020-02-04
 */
@Service
public class LyUserServiceImpl implements LyUserService {

    @Autowired
    LyUserMapper lyUserMapper;

    /**
     * 根據關鍵字查詢用戶
     *
     * @param keywords
     * @return
     */
    @Override
    public ArrayList<LyUser> getUserByUserKeywords(String keywords) {
        ArrayList<LyUser> users = new ArrayList<>();
        try {
            users = lyUserMapper.selectByKeywords(keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 根據客戶賬號查詢用戶是否已經存在
     *
     * @param userName
     * @return
     */
    @Override
    public LyUser checkUser(String userName) {
        LyUser user = new LyUser();
        user.setUserName(userName);
        return lyUserMapper.selectOne(user);
    }

    /**
     * 新增客戶
     *
     * @param user
     */
    @Override
    public int insetUser(LyUser user) {
        int i = 0;
        try {
            i = lyUserMapper.insertSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public int updateUser(LyUser user) {
        int i = 0;
        try {
            i = lyUserMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Override
    public int deleteUser(Integer userId) {
        int i = 0;
        try {
            i = lyUserMapper.deleteByPrimaryKey(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
