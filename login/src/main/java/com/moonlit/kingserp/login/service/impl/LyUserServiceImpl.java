package com.moonlit.kingserp.login.service.impl;

import com.moonlit.kingserp.entity.login.LyUser;
import com.moonlit.kingserp.login.mapper.LyUserMapper;
import com.moonlit.kingserp.login.service.LyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public int seleUserByUserName(String userName) {
        int nuber = 0;
        try {
            Example example = new Example(LyUser.class);
            example.createCriteria().andEqualTo("userName", userName);
            nuber = lyUserMapper.selectCountByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuber;
    }

    /**
     * 新增客戶
     *
     * @param user
     */
    @Override
    public void insetUser(LyUser user) {
        lyUserMapper.insertSelective(user);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void updateUser(LyUser user) {
        try {
            lyUserMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Override
    public void deleteUser(Integer userId) {
        try {
            lyUserMapper.deleteByPrimaryKey(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
