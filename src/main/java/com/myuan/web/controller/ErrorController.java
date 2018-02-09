package com.myuan.web.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/*
 * @author liuwei
 * @date 2018/2/9 15:03
 * 404
 */
@Controller
@ApiIgnore
@Log4j
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController{


    private static final String ERROR_PATH = "/error";
    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "system/404";
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
