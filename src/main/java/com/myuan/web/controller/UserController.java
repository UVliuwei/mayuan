package com.myuan.web.controller;

import com.google.common.base.Objects;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import com.myuan.web.service.UserService;
import com.myuan.web.utils.SaltPasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/1/28 13:04
 * user接口层
 */
@Api("用户接口层")
@RestController
@RequestMapping("api")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @GetMapping("/user/{id}")
    public MyUser getUser(@PathVariable("id") Long id) {
        MyUser user = userService.getUserById(id);
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    @ApiOperation(value = "密码修改", notes = "密码修改")
    @PutMapping("/user/{id}/pass")
    public MyResult updatePass(@PathVariable("id") Long id, String oldPass, String pass, String repass) {
        if (!Objects.equal(pass, repass)) {
            return MyResult.error("两次输入的密码不一致");
        }
        MyUser user = userService.getUserById(id);
        if (!user.getPassword().equals(SaltPasswordUtil.getNewPassword(oldPass))) {
            return MyResult.error("原密码不正确");
        }
        MyResult result = userService.updateUserPass(id, pass);
        return result;
    }

    @ApiOperation(value = "用户信息修改", notes = "用户信息修改")
    @PutMapping("/user/{id}")
    public MyResult updateInfo(@PathVariable("id") Long id, String name, String sex, String city, String description) {

        MyResult result = userService.updateUserInfo(id, name, sex, city, description);
        return result;
    }
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping("/user/passreset")
    public MyResult resetPass(String email) {
        MyResult result = userService.resetPass(email);
        return result;
    }
}
