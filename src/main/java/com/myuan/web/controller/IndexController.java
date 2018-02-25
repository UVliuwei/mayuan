package com.myuan.web.controller;

import com.myuan.web.entity.MyPost;
import com.myuan.web.service.AnswerService;
import com.myuan.web.service.PostService;
import com.myuan.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

/*
 * @author liuwei
 * @date 2018/1/19 10:57
 * 页面跳转
 */
@Controller
@ApiIgnore
public class IndexController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("user/{path}")
    public String user(@PathVariable("path") String path) {
        return ("user/" + path);
    }

    @GetMapping("jie/{path}")
    public String jie(@PathVariable("path") String path) {
        return ("jie/" + path);
    }

    @GetMapping("jie/edit/{id}")
    public String getPost(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "jie/edit";
    }

    @GetMapping("jie/detail/{id}")
    public String getPostDetail(@PathVariable("id") Long id, Model model,
                    @RequestParam(required = false, defaultValue = "1") Integer page,
                    @RequestParam(required = false, defaultValue = "6") Integer limit) {
        MyPost post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("page", answerService.findAnswers(id, page, limit));
        model.addAttribute("user", userService.getUserById(post.getUserId()));
        return "jie/detail";
    }
    @GetMapping("jie/detail/{id}/{page}")
    public String getPostDetailPage(@PathVariable("id") Long id, Model model,
                    @PathVariable("page") Integer page,
        @RequestParam(required = false, defaultValue = "6") Integer limit) {
        MyPost post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("page", answerService.findAnswers(id, page, limit));
        model.addAttribute("user", userService.getUserById(post.getUserId()));
        return "jie/detail";
    }
}
