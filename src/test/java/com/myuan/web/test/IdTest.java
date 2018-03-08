package com.myuan.web.test;
/*
 * @author liuwei
 * @date 2018/1/19 16:35
 *
 */

import lombok.NonNull;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

public class IdTest {

    @Test
    public void id() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String time = dateFormat.format(new Date());
        int random = (int) ((Math.random() + 1) * 100);
        System.out.println(time + random);
    }

    @Test
    public void NullPointTest() {
        @NonNull
        String s = null;

        System.out.println(s.length());

        System.out.println("test");

    }
}
