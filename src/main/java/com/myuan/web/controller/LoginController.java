package com.myuan.web.controller;

import com.myuan.web.entity.MyUser;
import com.myuan.web.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 登录 <liuwei> [2018/1/19 16:04]
     */
    @PostMapping("login")
    public void login(String email, String password) {
        System.out.println(email + "---" + password);
    }

    /**
     * 注册 <liuwei> [2018/1/20 9:33]
     */
    @PostMapping("reg")
    @ApiOperation(value = "用户注册",notes="用户注册")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user", value = "接受注册信息的实体类", dataType = "MyUser"),
        @ApiImplicitParam(name = "repassword", value = "确认密码", dataType = "String"),
        @ApiImplicitParam(name = "code", value = "验证码", dataType = "String")
    })
    public String register(MyUser user, String repassword, String code) {

        return null;
    }

    /**
     * <liuwei> [2018/1/19 15:47] 登录跳转 注册跳转
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
