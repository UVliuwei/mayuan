package com.myuan.web.entity;

import lombok.Getter;
import lombok.Setter;

/*
 * @author liuwei
 * @date 2018/1/20 8:35
 * 结果类
 */

public class MyResult {

    //-1 失败 、 0 成功
    private String code;
    private String msg;
    //json格式的返回数据
    private String data;
    //跳转的url
    private String action;

    public MyResult(String code, String msg, String data, String action) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.action = action;
    }

    public static MyResult action(String action, String msg) {
        return new MyResult("1", msg, null, action);
    }

    public static MyResult ok(String msg) {
        return new MyResult("1", msg, null, null);
    }

    public static MyResult data(String data) {
        return new MyResult("1", null, data, null);
    }

    public static MyResult error(String msg) {
        return new MyResult("-1", msg, null, null);
    }
}
