package com.moonlit.kingserp.common.response;

import lombok.Getter;

@Getter
public class Message {

    /**
     * 操作id
     */
    private int cmd;

    /**
     * 模块id
     */
    private int module;

    /**
     * 流水号
     */
    private int sn;

    /**
     * 数据
     */
    private String data;

    private Message setCmd(int cmd) {
        this.cmd = cmd;
        return this;
    }


    private Message setModule(int module) {
        this.module = module;
        return this;
    }


    private Message setSn(int sn) {
        this.sn = sn;
        return this;
    }

    private Message setData(String data) {
        this.data = data;
        return this;
    }

    public static Message valueOf(int sn, int module, int cmd, String data) {
        return valueOf(sn, module, cmd).setData(data);
    }

    private static Message valueOf(int sn, int module, int cmd) {
        return new Message().setSn(sn).setModule(module).setCmd(cmd);
    }
}
