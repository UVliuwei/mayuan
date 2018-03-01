package com.myuan.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.common.base.Objects;
import com.myuan.web.entity.MyPost;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.vo.UserPost;
import com.myuan.web.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/2/4 9:41
 * 帖子接口层
 */
@RestController
@Api("帖子接口层")
@RequestMapping("api")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @PostMapping("post")
    @ApiOperation(value = "发表求解", notes = "发表求解")
    public MyResult addPost(@Valid MyPost post, BindingResult bindingResult, String vercode, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!Objects.equal(code, vercode)) {
            return MyResult.error("验证码错误");
        }
        if (bindingResult.hasErrors()) {
            return validForm(bindingResult);
        }
        MyResult result = postService.savePost(post);
        return result;
    }

    @PutMapping("post")
    @ApiOperation(value = "编辑求解", notes = "编辑求解")
    public MyResult editPost(@Valid MyPost post, BindingResult bindingResult, String vercode, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!Objects.equal(code, vercode)) {
            return MyResult.error("验证码错误");
        }
        if (bindingResult.hasErrors()) {
            return validForm(bindingResult);
        }
        MyResult result = postService.editPost(post);
        return result;
    }

    @DeleteMapping("post/{id}")
    @ApiOperation(value = "删除求解", notes = "删除求解")
    public MyResult deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }

    @GetMapping("posts/user/{id}")
    @ApiOperation(value = "用户所有求解", notes = "用户所有求解")
    public JSONObject getUserPosts(
        @PathVariable("id") Long userId,
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer limit) {

        return postService.findUserPosts(userId, page, limit);
    }

    @PutMapping("post/{id}/topped/{type}")
    @ApiOperation(value = "求解置顶", notes = "求解置顶")
    public MyResult updateTop(@PathVariable("id") Long id, @PathVariable("type") String type) {
        return postService.updateTop(id, type);
    }

    @PutMapping("post/{id}/boutiqued/{type}")
    @ApiOperation(value = "求解加精", notes = "求解加精")
    public MyResult updateBoutique(@PathVariable("id") Long id, @PathVariable("type") String type) {
        return postService.updateBoutique(id, type);
    }

    @PutMapping("post/{id}/accepted/{ansId}")
    @ApiOperation(value = "求解采纳", notes = "求解采纳")
    public MyResult updateAccepted(@PathVariable("id") Long postId,@PathVariable("ansId") Long ansId) {
        return postService.updateAccepted(postId, ansId);
    }
    @GetMapping("/post/weektop")
    @ApiOperation(value = "本周热议", notes = "本周热议")
    public List<MyPost> getPostWeekTop() {
        return postService.getWeekTopPost();
    }

    @GetMapping("/post/top")
    @ApiOperation(value = "置顶帖", notes = "置顶帖")
    public List<UserPost> getPostTop() {
        return postService.getTopPost();
    }

}
