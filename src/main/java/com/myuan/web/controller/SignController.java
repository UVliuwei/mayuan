package com.myuan.web.controller;

import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MySign;
import com.myuan.web.entity.MyUser;
import com.myuan.web.service.SignService;
import com.myuan.web.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/3/2 10:22
 * 签到
 */
@Api("用户签到")
@RestController
@RequestMapping("api")
public class SignController {

    @Autowired
    private SignService signService;

    @PostMapping("sign/{userId}")
    @ApiOperation(value = "用户签到", notes = "用户签到")
    public MyResult sign(@PathVariable("userId") Long userId) {
        return signService.sign(userId);
    }
    @GetMapping("sign/status")
    @ApiOperation(value = "用户签到状态", notes = "用户签到状态")
    public MyResult signStatus() {
        MyUser user = UserUtil.getCurrentUser();
        if(user != null) {
            return signService.signStatus(user.getId());
        }
        return MyResult.error("");
    }
    @GetMapping("sign/rank")
    @ApiOperation(value = "签到排行", notes = "签到排行")
    public MyResult signRank() {
        return signService.signAllInfo();
    }

}
