package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/2/4 10:05
 * post持久层
 */

import com.myuan.web.entity.MyPost;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends BaseDao<MyPost>{

    MyPost findMyPostById(Long id);
}
