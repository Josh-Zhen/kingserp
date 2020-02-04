package com.moonlit.kingserp.common.response;

import com.moonlit.kingserp.common.errer.ErrerMsg;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value = "服务端响应基础类")
public class ResponseObj<T> implements Serializable {

    public static final int SUCCESS_CODE = 200;

    public static final String SUCCESS_MSG = "发送成功";

    private int code;

    private T data;

    private String message;

    public int getCode() {

        return code;
    }

    public T getData() {

        return data;
    }

    public String getMessage() {

        return message;
    }

    public ResponseObj setCode(int code) {
        this.code = code;
        return this;
    }

    public ResponseObj setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseObj setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseObj(ResponseSeckillEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.message = resultEnums.getMessage();
    }

    public ResponseObj(ResponseSeckillEnums resultEnums, T data) {
        this.code = resultEnums.getCode();
        this.message = resultEnums.getMessage();
        this.data = data;
    }

    public ResponseObj() {
    }

    public static <T> ResponseObj<T> createResponse(int code, String msg) {

        return new ResponseObj<T>().setCode(code).setMessage(msg);
    }

    public static <T> ResponseObj<T> createSuccessResponse(T obj) {

        return createResponse(SUCCESS_CODE, SUCCESS_MSG).setData(obj);
    }

    public static <T> ResponseObj<T> createSuccessResponse() {

        return createResponse(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static ResponseObj createErrResponse(ErrerMsg errerMsg) {

        return createResponse(errerMsg.getCode(), errerMsg.getMessage());
    }

    public static ResponseObj createErrResponse(String errerMsg) {

        return createResponse(100, errerMsg);
    }

    @Override
    public String toString() {
        return "{code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' + "}";
    }
}
