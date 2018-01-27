package com.myuan.web.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/*
 * @author liuwei
 * @date 2018/1/20 8:35
 * 结果类
 */
@Data
public class MyResult {

    //-1 失败 、 0 成功
    private String status;
    private String msg;
    //json格式的返回数据
    private String data;
    //跳转的url
    private String action;

    public MyResult(String status, String msg, String data, String action) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        if (action != null) {
            this.action = action;
        } else {
            this.action = action;
        }
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
