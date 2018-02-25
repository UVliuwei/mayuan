package com.myuan.web.dao;

import com.myuan.web.entity.MyAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author liuwei
 * @date 2018/2/12 10:38
 *
 */
@Repository
public interface AnswerDao extends BaseDao<MyAnswer>{

    Page<MyAnswer> findMyAnswersByPostId(Long postId, Pageable pageable);

    @Modifying
    @Transactional
    void deleteById(Long id);
}
