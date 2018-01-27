package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/1/20 22:30
 *
 */

import com.myuan.web.entity.MyRoleAuth;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAuthDao extends BaseDao<MyRoleAuth> {

    List<MyRoleAuth> findMyRoleAuthsByRoleId(String roleId);
}
