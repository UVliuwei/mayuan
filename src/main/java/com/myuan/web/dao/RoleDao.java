package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/1/20 22:08
 *
 */

import com.myuan.web.entity.MyRole;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends BaseDao<MyRole> {

    List<MyRole> findMyRolesByIdIn(List<Long> ids);

    MyRole findMyRoleByType(String type);
}
