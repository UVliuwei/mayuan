package com.myuan.web.config;

import com.myuan.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @author liuwei
 * @date 2018/2/26 15:51
 * 定时任务
 */
@Component
public class ScheduleTask {

    @Autowired
    private PostService postService;

//    @Scheduled(cron = "40/10 * * * * ?")
//    public void updateSession() {
//
//    }
}
