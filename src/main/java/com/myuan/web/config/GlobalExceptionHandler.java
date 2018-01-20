package com.myuan.web.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * @author liuwei
 * @date 2018/1/20 13:16
 * 全局异常处理，接受controller抛出的异常
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "404";

    @ExceptionHandler(value = Exception.class)
    //@ResponseBody 可返回json
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e)
        throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("system/404");
        return mav;
    }
}
