package com.myuan.web.utils;
/*
 * @author liuwei
 * @date 2018/1/19 16:45
 *
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {

    public static String getId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String time = dateFormat.format(new Date());
        int random=(int) ((Math.random()+1)*100);
        return random + time;
    }
}
