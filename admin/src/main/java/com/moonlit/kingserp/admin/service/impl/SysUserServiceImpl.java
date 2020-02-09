package com.moonlit.kingserp.admin.service.impl;

import com.moonlit.kingserp.admin.mapper.SysUserMapper;
import com.moonlit.kingserp.admin.mapper.SysUserRoleMapper;
import com.moonlit.kingserp.admin.service.SysUserService;
import com.moonlit.kingserp.common.exception.EpException;
import com.moonlit.kingserp.common.util.CommonUtil;
import com.moonlit.kingserp.common.util.MD5Util;
import com.moonlit.kingserp.entity.admin.SysUser;
import com.moonlit.kingserp.entity.admin.SysUserRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * 系统用户 服务实现类
 *
 * @author Joshua
 * @since 2020-02-08
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 当前登录用户的权限等信息
     *
     * @return userInfo
     */
    @Override
    public SysUser getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        SysUser userInfo = (SysUser) session.getAttribute("tokens");
        if (userInfo == null) {
            throw new EpException("token失效，请重新登录", 401);
        }
        return userInfo;
    }

    /**
     * 校驗用戶是否存在
     *
     * @param sysUser
     * @return SysUser
     */
    @Override
    public SysUser exitSysUser(SysUser sysUser) {
        SysUser user = new SysUser();
        user.setUserName(sysUser.getUserName());
        return sysUserMapper.selectOne(user);
    }

    /**
     * 添加一個成員
     *
     * @param sysUser
     * @return
     */
    @Override
    public int addSysUser(SysUser sysUser) {
        int i = 0;
        try {
            String userSalt = CommonUtil.getVerificationCode(6);
            sysUser.setPassword(MD5Util.getMd5insalf(sysUser.getPassword(), userSalt));
            sysUser.setCreateTime(new Date());
            sysUser.setUserSalt(userSalt);
            i = sysUserMapper.insertSelective(sysUser);

            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysUser.getRoleId());
            sysUserRole.setUserId(sysUser.getId());
            i = sysUserRoleMapper.insertSelective(sysUserRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 修改成員信息
     *
     * @param sysUser
     */
    @Override
    public int updateSysUser(SysUser sysUser) {
        int i = 0;
        try {
            i = sysUserMapper.updateByPrimaryKeySelective(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 刪除成員
     *
     * @param sysUserId
     * @return
     */
    @Override
    public int delSysUserById(Integer sysUserId) {
        int i = 0;
        SysUser sysUser = new SysUser();
        sysUser.setId(sysUserId);
        try {
            i = sysUserMapper.delete(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 模糊查詢管理者
     *
     * @param keywords
     * @return
     */
    @Override
    public ArrayList<SysUser> selectSysUsers(String keywords) {
        ArrayList<SysUser> sysUsers = null;
        try {
            sysUsers = sysUserMapper.selectUserByUserKeywords(keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUsers;
    }

    /**
     * 獲取當前用戶
     *
     * @param userName
     * @return
     */
    @Override
    public SysUser getUserInfo(String userName) {
        try {
            return sysUserMapper.userInfo(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}