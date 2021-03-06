package com.myuan.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.myuan.web.entity.MyAnswer;
import com.myuan.web.entity.MyPage;
import com.myuan.web.entity.MyReply;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import com.myuan.web.entity.vo.UserAnswer;
import com.myuan.web.service.AnswerService;
import com.myuan.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/2/12 8:58
 * 回复接口层
 */
@RestController
@Api("回复接口层")
@RequestMapping("api")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("answer")
    @ApiOperation(value = "添加回复", notes = "添加回复")
    public MyResult addReply(String content, Long postId, Long userId) {
        MyResult result = answerService.addAnswer(userId, postId, content);
        return result;
    }

    @ApiOperation(value = "未读消息条数", notes = "未读消息条数")
    @GetMapping("message/nums")
    public MyResult messageNum(Long userId) {
        Integer num = answerService.getMessageNums(userId);
        return MyResult.data(num.toString());
    }

    @ApiOperation(value = "回答", notes = "回答")
    @GetMapping("/post/{id}/answers")
    public MyPage<MyAnswer> findPostAnswers(@PathVariable("id") Long postId,
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "6") Integer limit) {

        return answerService.findAnswers(postId, page, limit);
    }

    @ApiOperation(value = "未读消息", notes = "未读消息")
    @GetMapping("user/{id}/messages")
    public MyResult message(@PathVariable("id") Long id) {
        return answerService.findUserMessage(id);
    }

    @ApiOperation(value = "删除消息", notes = "删除消息")
    @DeleteMapping("message/{id}")
    public MyResult deleteMessage(@PathVariable("id") Long id) {
        return answerService.deleteMessage(id);
    }

    @ApiOperation(value = "删除用户全部消息", notes = "删除用户全部消息")
    @DeleteMapping("/user/{id}/messages")
    public MyResult deleteUserMessages(@PathVariable("id") Long id) {
        return answerService.deleteMessages(id);
    }

    @ApiOperation(value = "删除回答", notes = "删除回答")
    @DeleteMapping("answer/{id}/{flag}")
    public MyResult deleteAnswer(@PathVariable("id") Long id, @PathVariable("flag") String flag) {
        return answerService.deleteAnswer(id, flag);
    }

    @ApiOperation(value = "回帖周榜", notes = "回帖周榜")
    @GetMapping("answer/top/users")
    public List<UserAnswer> getTopUsers() {
        return answerService.findTopAnswerUsers();
    }
}
