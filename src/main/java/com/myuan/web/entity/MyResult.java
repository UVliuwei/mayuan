package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:36
 * 返回json类
 */

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class MyResult extends JSONObject implements Serializable{

    private static final long serialVersionUID = -7615922256393539948L;

    /**
     * status 0出错、1正常
     * @return
     */
    public static String ok() {
        JSONObject result = new MyResult();
        result.put("status", "1");
        return result.toJSONString();
    }
    public static String ok(String msg) {
        JSONObject result = new MyResult();
        result.put("status", "1");
        result.put("msg", msg);
        return result.toJSONString();
    }
    public static String ok(String msg,String data) {
        JSONObject result = new MyResult();
        result.put("status", "1");
        result.put("msg", msg);
        result.put("data", data);
        return result.toJSONString();
    }
    public static String action(String action,String msg) {
        JSONObject result = new MyResult();
        result.put("status", "1");
        result.put("msg", msg);
        result.put("action", action);
        return result.toJSONString();
    }
    public static String action(String action) {
        JSONObject result = new MyResult();
        result.put("status", "1");
        result.put("action", action);
        return result.toJSONString();
    }
    public static String error(String msg) {
        JSONObject result = new MyResult();
        result.put("status", "0");
        result.put("msg", msg);
        return result.toJSONString();
    }
}
