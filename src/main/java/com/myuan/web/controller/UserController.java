package com.myuan.web.controller;

import com.myuan.web.entity.MyUser;
import com.myuan.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/1/28 13:04
 *
 */
@Api("用户控制层")
@RestController
@RequestMapping("api")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @GetMapping("/user/{id}")
    public MyUser getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
