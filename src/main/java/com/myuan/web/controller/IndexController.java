package com.myuan.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
 * @author liuwei
 * @date 2018/1/19 10:57
 *
 */
@RestController
public class IndexController {

    @GetMapping(value = {"/","index"})
    public ModelAndView hello(Model model) {
        return new ModelAndView("index");
    }
}
