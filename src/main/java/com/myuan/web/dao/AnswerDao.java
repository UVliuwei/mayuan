package com.myuan.web.dao;

import com.myuan.web.entity.MyAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/*
 * @author liuwei
 * @date 2018/2/12 10:38
 *
 */
@Repository
public interface AnswerDao extends BaseDao<MyAnswer>{

    Page<MyAnswer> findMyAnswersByPostId(Long postId, Pageable pageable);
}
