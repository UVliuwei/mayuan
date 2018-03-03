package com.myuan.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.myuan.web.entity.MyPost;
import com.myuan.web.entity.MyUser;
import com.myuan.web.service.AnswerService;
import com.myuan.web.service.PostService;
import com.myuan.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

    @GetMapping("user/info")
    public String userInfo(String username) {
        MyUser user = userService.getUserByName(username);
        return "redirect: /user/" + user.getId() + "/info";
    }
    @GetMapping("jie/{path}")
    public String jie(@PathVariable("path") String path) {
        return ("jie/" + path);
    }
    @GetMapping("user/{path}")
    public String user(@PathVariable("path") String path) {
        return ("user/" + path);
    }
    @GetMapping(value = {"/", "index"})
    public String index(Model model) {
        model.addAttribute("topList", postService.getTopPost());
        model.addAttribute("page", postService.getAllPost(1, 30, "all", "all"));
        return "index";
    }
    @GetMapping(value = {"/column/{column}/{value}/{page}", "/column/{column}/{value}"})
    public String GetAllPost(@PathVariable("column") String column
        ,@PathVariable("value") String value, Model model
        ,@PathVariable(value = "page", required = false) String page) {
        Integer p = 1;
        if(!StringUtils.isBlank(page) && StringUtils.isNumeric(page)) {
            p = Integer.valueOf(page);
        }
        model.addAttribute("page", postService.getAllPost(p, 30, column, value));
        return "/jie/index";
    }

    @GetMapping("user/{id}/info")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        JSONObject posts = postService.findUserPosts(id, 1, 30);
        model.addAttribute("posts", posts.get("data"));
        model.addAttribute("answers", answerService.findUserAnswers(id));
        return "user/home";
    }

    @GetMapping("jie/edit/{id}")
    public String getPost(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "jie/edit";
    }
    @GetMapping(value = {"jie/detail/{id}", "jie/detail/{id}/{page}"})
    public String getPostDetailPage(@PathVariable("id") Long id, Model model,
        @PathVariable(value = "page", required = false) String page,
        @RequestParam(required = false, defaultValue = "6") Integer limit) {
        Integer p = 1;
        if(!StringUtils.isBlank(page) && StringUtils.isNumeric(page)) {
            p = Integer.valueOf(page);
        }
        MyPost post = postService.getPostById(id);
        postService.addPostPopularity(id);
        model.addAttribute("post", post);
        model.addAttribute("page", answerService.findAnswers(id, p, limit));
        model.addAttribute("user", userService.getUserById(post.getUserId()));
        return "jie/detail";
    }
}
