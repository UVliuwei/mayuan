package com.myuan.web.controller;

import com.myuan.web.entity.MyPost;
import com.myuan.web.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.annotations.ApiIgnore;

/*
 * @author liuwei
 * @date 2018/1/19 10:57
 * 页面跳转
 */
@Controller
@ApiIgnore
public class IndexController {

    @Autowired
    private PostService postService;

    @GetMapping("user/{path}")
    public String user(@PathVariable("path") String path) {
        return ("user/" + path);
    }

    @GetMapping("jie/{path}")
    public String jie(@PathVariable("path") String path) {
        return ("jie/" + path);
    }



}
