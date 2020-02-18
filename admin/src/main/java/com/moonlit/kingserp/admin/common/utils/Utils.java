package com.moonlit.kingserp.admin.common.utils;

import com.moonlit.kingserp.admin.common.shiro.ShiroUtils;

/**
 * @Description: 工具類
 * @Author: Joshua
 * @CreateDate: 2020-02-18 18:21
 * @Version 1.0
 */
public class Utils {

    /**
     * 判斷用戶是否是超級管理者
     *
     * @return
     */
    public static boolean checkUserIsSuper() {
        return ShiroUtils.getUserInfo().getUserIsSuper() == 1;
    }


}
