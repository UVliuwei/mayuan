package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/2/4 10:05
 * post持久层
 */

import com.myuan.web.entity.MyPost;
import java.util.Date;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostDao extends BaseDao<MyPost> {

    MyPost findMyPostById(Long id);

    @Modifying
    @Transactional
    @Query("update MyPost post set post.title = ?2, post.content = ?3, post.updateDate = ?4 where post.id = ?1")
    void updatePost(Long id, String title, String content, Date updateDate);
}
