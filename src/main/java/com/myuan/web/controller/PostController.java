package com.myuan.web.controller;

import com.myuan.web.entity.MyPost;
import com.myuan.web.entity.MyResult;
import com.myuan.web.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author liuwei
 * @date 2018/2/4 9:41
 * 帖子接口层
 */
@RestController
@Api("提问接口层")
@RequestMapping("api")
public class PostController extends BaseController{

    @Autowired
    private PostService postService;

    @PostMapping("post")
    @ApiOperation(value = "发帖", notes = "发帖")
    public MyResult addPost(@Valid MyPost post, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return validForm(bindingResult);
        }
        MyResult result = postService.savePost(post);
        return result;
    }
}
