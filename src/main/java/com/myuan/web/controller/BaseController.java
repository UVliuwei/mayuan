package com.myuan.web.controller;

//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.apache.shiro.authz.annotation.RequiresUser;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//import com.fly.pojo.FlyResult;
//import com.fly.pojo.FlyUser;
//import com.google.common.collect.Maps;

public class BaseController {
	
	/**
     * 登录认证异常
     */
//    @ExceptionHandler({ UnauthenticatedException.class })
//	protected String authenticationException(HttpServletRequest request, HttpServletResponse response) {
//        if (isAjaxRequest(request)) {
//            // 输出JSON
//            writeJson(FlyResult.error("未登录"), response);
//            return null;
//        } else {
//            return "redirect:/user/login";
//        }
//    }
//
//    /**
//     * 权限异常
//     */
//    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
//    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
//        if (isAjaxRequest(request)) {
//            // 输出JSON
//            writeJson(FlyResult.error("无权限访问"), response);
//            return null;
//        } else {
//            return "redirect:/system/403";
//        }
//    }
//
//    /**
//     * 输出JSON
//     */
//    private void writeJson(String msg, HttpServletResponse response) {
//        PrintWriter out = null;
//        try {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            out = response.getWriter();
//            out.write(JSON.toJSONString(msg));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }
//    }
//    /**
//     * 是否是Ajax请求
//     */
//    public static boolean isAjaxRequest(HttpServletRequest request) {
//        String requestedWith = request.getHeader("x-requested-with");
//        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * shiro session
//     */
//    public void setUserSession(FlyUser user) {
//    	Subject subject = SecurityUtils.getSubject();
//    	Session session = subject.getSession(true);
//    	session .setAttribute("user", user);
//    }
}

	

