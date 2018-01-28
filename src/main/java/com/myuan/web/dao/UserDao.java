package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/1/19 18:00
 * user持久层
 */

import com.myuan.web.entity.MyUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<MyUser> {

    MyUser findMyUsersByName(String name);
    MyUser findMyUsersByEmail(String email);
    MyUser findMyUsersById(Long id);
}
