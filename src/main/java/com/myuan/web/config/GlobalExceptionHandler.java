package com.myuan.web.config;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.ShiroException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * @author liuwei
 * @date 2018/1/20 13:16
 * 全局异常处理，接受controller抛出的异常
 */

@ControllerAdvice(annotations = {Controller.class})
@Log4j
public class GlobalExceptionHandler {

    public static final String DEFAULT_NOAUTH_VIEW = "noauthc";

    @ExceptionHandler(ShiroException.class)
    public ModelAndView shiroException(Exception ex) {
        log.info("无权限访问");
        return new ModelAndView("system/" + DEFAULT_NOAUTH_VIEW);
    }


}
