package com.myuan.web.controller;

import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import com.myuan.web.service.UserService;
import com.myuan.web.utils.SaltPasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/*
 * @author liuwei
 * @date 2018/1/19 14:28
 *
 */
@Log4j
@Api("用户注册登录")
@RestController
@RequestMapping("user")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 登录 <liuwei> [2018/1/19 16:04]
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public MyResult login(String email, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email,SaltPasswordUtil.getNewPassword(password));
        try {
            subject.login(token);
        } catch (LockedAccountException lock) {
            log.info("账号已被锁定");
            return MyResult.error("账号已被锁定");
        } catch (Exception e) {
            return MyResult.error("用户名或密码错误");
        }
        MyUser user=userService.getUserByEmail(email);
        setUserSession(user);
        return MyResult.action("/user/index","登录成功");
    }

    /**
     * 注册 <liuwei> [2018/1/20 9:33]
     */
    @PostMapping("reg")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public MyResult register(@Valid MyUser user, BindingResult bindingResult, String repassword) {
        if(bindingResult.hasErrors()) {
            return validForm(bindingResult);
        }
        if (!user.getPassword().equals(repassword)) {
            return MyResult.error("两次输入的密码不一致");
        }
        user.setPassword(SaltPasswordUtil.getNewPassword(user.getPassword()));
        MyResult result = userService.saveUser(user);
        return result;
    }

    /**
     * <liuwei> [2018/1/19 15:47] 登录跳转 注册跳转
     */
    @GetMapping("login")
    @ApiIgnore
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }

    @GetMapping("reg")
    @ApiIgnore
    public ModelAndView register() {
        return new ModelAndView("user/reg");
    }
}
