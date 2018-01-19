package com.myuan.web.test;
/*
 * @author liuwei
 * @date 2018/1/19 16:35
 *
 */

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdTest {

    @Test
    public void id() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String time = dateFormat.format(new Date());
        int random=(int) ((Math.random()+1)*100);
        System.out.println(time + random);
    }
}
