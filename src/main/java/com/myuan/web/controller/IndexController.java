package com.myuan.web.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.annotations.ApiIgnore;

/*
 * @author liuwei
 * @date 2018/1/19 10:57
 *
 */
@Controller
@ApiIgnore
public class IndexController extends WebMvcConfigurerAdapter {

    /**
     * 页面跳转 <liuwei> [2018/1/27 10:52]
     */
    @GetMapping("user/{path}")
    public String login(@PathVariable("path") String path) {
        return ("user/" + path);
    }

}
