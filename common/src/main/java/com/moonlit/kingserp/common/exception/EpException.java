package com.moonlit.kingserp.common.exception;

/**
 * 自定义异常
 * @author Administrator
 * @create 2019-04-27 17:39
 **/
public class EpException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public EpException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public EpException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public EpException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public EpException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
