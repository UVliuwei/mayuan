package com.myuan.web.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.annotations.ApiIgnore;

/*
 * @author liuwei
 * @date 2018/1/19 10:57
 *
 */
@Configuration
public class IndexController extends WebMvcConfigurerAdapter {


    /**
     * 页面跳转 <liuwei> [2018/1/27 10:52]
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("myuan/user/login").setViewName("user/login");
        registry.addViewController("/user/reg").setViewName("user/reg");
        registry.addViewController("/user/set").setViewName("user/set");
    }
}
