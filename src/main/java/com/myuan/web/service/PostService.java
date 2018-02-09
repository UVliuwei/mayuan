package com.myuan.web.service;

import com.myuan.web.dao.PostDao;
import com.myuan.web.entity.MyPost;
import com.myuan.web.entity.MyResult;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author liuwei
 * @date 2018/2/4 10:06
 * post业务层
 */
@Service
@Log4j
public class PostService {
    @Autowired
    private PostDao postDao;

    public MyResult savePost(MyPost post) {
        try {
            post.preInsert();
            post.setAnsnum(0);
            post.setBoutiqued("0");
            post.setTopped("0");
            post.setPopularity(0);
            post.setAnsnum(0);
            post.setEnded("0");
            postDao.save(post);
            log.info("发帖成功");
            return MyResult.action("/jie/index", "问题发布成功");
        }catch (Exception e) {
            log.info("发帖失败" + e.toString());
        }
        return MyResult.error("系统错误，请重试");
    }
    public MyPost getPostById(Long id) {
        return postDao.findMyPostById(id);
    }
}
