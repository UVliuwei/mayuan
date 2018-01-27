package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/1/20 22:19
 *
 */

import com.myuan.web.entity.MyUserRole;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends BaseDao<MyUserRole> {

    List<MyUserRole> findMyUserRolesByUserId(String userId);

}
