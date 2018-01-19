package com.myuan.web.controller;

import com.myuan.web.entity.MyUser;
import com.myuan.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
 * @author liuwei
 * @date 2018/1/19 14:28
 *
 */
@RestController
@RequestMapping("user")
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * <liuwei> [2018/1/19 16:04]
     * @param email
     * @param password
     */
    @PostMapping("login")
    public void login(String email, String password) {
        System.out.println(email +"---" + password);
    }
    @PostMapping("reg")
    public String register(String email) {
        return null;
    }
    @GetMapping("user")
    public MyUser getUser(String name) {
        return userService.getUserByName(name);
    }
    /**
     * <liuwei> [2018/1/19 15:47]
     * 登录跳转
     * 注册跳转
     * @return
     */
    @GetMapping("login")
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }
    @GetMapping("reg")
    public ModelAndView register() {
        return new ModelAndView("user/reg");
    }
}
