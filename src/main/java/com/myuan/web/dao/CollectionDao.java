package com.myuan.web.dao;

import com.myuan.web.entity.MyCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author liuwei
 * @date 2018/2/11 14:21
 *
 */
@Repository
public interface CollectionDao extends BaseDao<MyCollection>{

    MyCollection findByUserIdAndPostId(Long userId,Long postId);

    @Transactional
    void deleteByUserIdAndPostId(Long userId,Long postId);

    Page<MyCollection> findMyCollectionsByUserId(Long userId, Pageable pageable);
}
