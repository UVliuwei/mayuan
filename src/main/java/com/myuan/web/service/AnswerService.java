package com.myuan.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.myuan.web.dao.AnswerDao;
import com.myuan.web.dao.ReplyDao;
import com.myuan.web.entity.MyAnswer;
import com.myuan.web.entity.MyPage;
import com.myuan.web.entity.MyPost;
import com.myuan.web.entity.MyReply;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import com.myuan.web.entity.vo.UserAnswer;
import com.myuan.web.utils.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author liuwei
 * @date 2018/2/12 9:57
 *
 */
@Service
@Log4j
public class AnswerService {

    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    /**
     * <liuwei> [2018/2/24 9:34] 发表回复
     */
    @Transactional
    public MyResult addAnswer(Long userId, Long postId, String content) {
        try {
            MyUser user = userService.getUserById(userId);
            MyAnswer answer = new MyAnswer();
            answer.setContent(content.trim());
            answer.setPostId(postId);
            answer.setUserId(userId);
            answer.setLikes(0);
            answer.setUserImg(user.getImg());
            answer.setUserName(user.getName());
            answer.preInsert();
            answerDao.save(answer);
            String[] strings = content.trim().split(" ");
            String reg = "@.*";
            boolean matches = Pattern.matches(reg, strings[0]);
            String html = "<li><div class=\"detail-about detail-about-reply\"><a class=\"fly-avatar\" href=\"/user/" + user.getId()
                + "/info\" target=\"_blank\"><img src=\"/images/" + user.getImg() + "\" alt=\"" + user.getName()
                + "\"></a><div class=\"fly-detail-user\"><a href=\"/user/" + user.getId() + "/info\" target=\"_blank\" class=\"fly-link\"><cite>"
                + user.getName()
                + "</cite></a></div><div class=\"detail-hits\"><span>刚刚</span></div></div><div id='ahtml' class=\"detail-body jieda-body photos\">"
                + content + "</div></li>";
            if (matches) {
                MyReply reply = new MyReply();
                MyUser replyUser = userService.getUserByName(strings[0].substring(1));
                reply.setPostId(postId);
                reply.setPostName(postService.getPostById(postId).getTitle());
                reply.setUserId(userId);
                reply.setUserName(user.getName());
                if (replyUser == null) {
                    reply.setReplyId(-1L);
                } else {
                    reply.setReplyId(user.getId());
                }
                reply.setReplyName(strings[0].substring(1));
                reply.preInsert();
                replyDao.save(reply);
                postService.addPostAnsNum(postId);
            }
            return MyResult.data(html);
        } catch (Exception e) {
            log.info("回复失败：" + e.toString());
        }
        return MyResult.error("系统异常，请重试");
    }

    public Integer getMessageNums(Long userId) {
        return replyDao.countByReplyId(userId);
    }

    public MyResult findUserMessage(Long id) {
        List<MyReply> replies = replyDao.findMyRepliesByReplyId(id);
        return MyResult.data(JSON.toJSONString(replies));
    }

    public MyResult deleteMessage(Long id) {
        MyUser user = userService.getUserById(id);
        replyDao.deleteById(id);
        return MyResult.ok("");
    }

    public MyResult deleteMessages(Long userId) {
        replyDao.deleteAllByReplyId(userId);
        return MyResult.ok("");
    }

    @Transactional
    public MyResult deleteAnswer(Long id, String flag) {
        try {
            answerDao.deleteById(id);
            if ("true".equals(flag)) {
                postService.removeAccept(id);
            }
            return MyResult.ok("");
        } catch (Exception e) {
            log.info("回答删除失败：" + e.toString());
        }
        return MyResult.error("系统异常，请重试");
    }

    /**
     * <liuwei> [2018/2/24 14:08] 回复分页
     */
    public MyPage<MyAnswer> findAnswers(Long postId, Integer page, Integer limit) {
        Sort sort = new Sort(Direction.ASC, "createDate");
        Pageable pageable = new PageRequest(page - 1, limit, sort);
        Page<MyAnswer> answers = answerDao.findMyAnswersByPostId(postId, pageable);
        MyPage<MyAnswer> myPage = new MyPage<>();
        if (answers.getTotalElements() == 0) {
            myPage.setCount(0L);
            myPage.setCurrentPage(page);
            myPage.setPageNum(0);
            myPage.setList(new ArrayList<MyAnswer>());
        } else {
            myPage.setCount(answers.getTotalElements());
            myPage.setCurrentPage(page);
            myPage.setPageNum(answers.getTotalPages());
            myPage.setList(answers.getContent());
        }
        return myPage;
    }

    /**
     * <liuwei> [2018/2/26 8:36] 用户回答
     */
    public List<MyAnswer> findUserAnswers(Long userId) {
        return answerDao.findMyAnswersByUserId(userId);
    }

    public MyAnswer findAnswerById(Long id) {
        return answerDao.findOne(id);
    }

    /**
     * <liuwei> [2018/2/27 14:13] 回帖周榜
     */
    @Transactional
    public List<UserAnswer> findTopAnswerUsers() {
        Sort sort = new Sort(Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(0, 12);
        Date date = DateUtil.getThisWeek();
        Page<Long> userAnswers = answerDao.findTopAnswerUsers(date, pageable);
        List<UserAnswer> userList = Lists.newArrayList();
        UserAnswer userAnswer = null;
        for (Long id : userAnswers.getContent()) {
            userAnswer = new UserAnswer();
            userAnswer.setUser(userService.getUserById(id));
            userAnswer.setCount(answerDao.countByUserIdAndCreateDateAfter(id, date));
            userList.add(userAnswer);
        }
        return userList;
    }

}
