package com.myuan.web.service;

import com.alibaba.fastjson.JSONObject;
import com.myuan.web.dao.PostDao;
import com.myuan.web.entity.MyPost;
import com.myuan.web.entity.MyResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        } catch (Exception e) {
            log.info("发帖失败" + e.toString());
        }
        return MyResult.error("系统错误，请重试");
    }

    public MyPost getPostById(Long id) {
        return postDao.findMyPostById(id);
    }

    public MyResult editPost(MyPost post) {
        try {
            post.preUpdate();
            postDao.updatePost(post.getId(), post.getTitle(), post.getContent(), post.getUpdateDate());
            return MyResult.action("/jie/index", "问题编辑成功");
        } catch (Exception ex) {
            log.info("问题更新失败：" + ex.toString());
        }
        return MyResult.error("系统异常，请重试");
    }

    /**
     * <liuwei> [2018/2/10 11:16] post分页 pageable 分页默认第0页开始
     */
    public JSONObject findUserPosts(Long userId, Integer page, Integer limit) {
        JSONObject object = new JSONObject();
        Sort sort = new Sort(Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(page - 1, limit, sort);
        Page<MyPost> postPage = postDao.findMyPostsByUserId(userId, pageable);
        if(postPage.getTotalElements() == 0) {
            object.put("code", "0");
            object.put("msg", "");
            object.put("count", "0");
            object.put("data", new ArrayList<MyPost>());
            return object;
        }
        object.put("code", "0");
        object.put("msg", "");
        object.put("count", postPage.getTotalElements() + "");
        object.put("data", postPage.getContent());
        return object;
    }

    public MyResult deletePost(Long id) {
        postDao.deleteById(id);
        return MyResult.action("/jie/index", "帖子删除成功");
    }

    /**
     * <liuwei> [2018/2/11 13:58] 置顶，取消置顶
     */
    public MyResult updateTop(Long id, String type) {
        postDao.updateTop(id, type, new Date());
        return MyResult.ok("");
    }
    /**
     * <liuwei> [2018/2/11 14:01] 加精，取消加精
     */
    public MyResult updateBoutique(Long id, String type) {
        postDao.updateBoutique(id, type, new Date());
        return MyResult.ok("");
    }
}
