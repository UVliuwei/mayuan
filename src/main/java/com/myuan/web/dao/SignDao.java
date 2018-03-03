package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/3/2 10:26
 *
 */

import com.myuan.web.entity.MySign;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface SignDao extends BaseDao<MySign> {

    MySign findByUserId(Long userId);

    Page<MySign> findByContinueNumGreaterThanAndUpdateDateAfter(int num, Date date, Pageable pageable);

    Page<MySign> findByContinueNumGreaterThan(int num, Pageable pageable);
}
