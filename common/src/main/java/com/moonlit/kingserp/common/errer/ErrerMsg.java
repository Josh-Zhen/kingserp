package com.moonlit.kingserp.common.errer;

import lombok.Getter;

/**
 * 错误返回定义
 */
@Getter
public enum ErrerMsg {

    ERRER100(100, "Request exception"),
    ERRER420(420, "Parameter error"),
    ERRER600(600, "Database update exception"),
    ERRER500(500, "系统内部错误"),
    ERRER510(510, "Upload exception"),
    ERRER10001(10001, "密码错误"),
    ERRER10002(10002, "用户不存在"),
    ERRER10003(10003, "用户已锁定,请联系管理员"),
    ERRER10004(10004, "用户未登录"),
    ERRER10005(10005, "手机号码格式错误"),
    ERRER10006(10006, "手机号码重复验证"),
    ERRER10007(10007, "验证码发送失败"),
    ERRER10008(10008, "手机没有认证或已过期"),
    ERRER10009(10009, "验证码错误"),
    ERRER10010(10010, "密码不能为空"),
    ERRER10011(10011, "该手机号码已注册"),
    ERRER10012(10012, "注册失败"),
    ERRER20502(20502, "更新失败"),
    ERRER20505(20505, "删除失败"),
    ERRER20506(20506, "修改失败"),
    ERRER20509(20509, "添加失败");

    private int code;
    private String message;

    ErrerMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
