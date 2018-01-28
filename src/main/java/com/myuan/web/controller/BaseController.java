package com.myuan.web.controller;
/*
 * @author liuwei
 * @date 2018/1/27 12:35
 *
 */

import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;

public class BaseController {

    /**
     * 表单验证
     * <liuwei> [2018/1/27 12:41]
     */
    protected MyResult validForm(BindingResult result) {
            String message = result.getFieldError().getDefaultMessage();
            return MyResult.error(message);
    }
    /**
     * shiro session
     * <liuwei> [2018/1/28 11:48]
     */
    public void setUserSession(MyUser user) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        session .setAttribute("user", user);
    }
}
