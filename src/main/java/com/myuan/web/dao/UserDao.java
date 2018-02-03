package com.myuan.web.dao;
/*
 * @author liuwei
 * @date 2018/1/19 18:00
 * user持久层
 */

import com.myuan.web.entity.MyUser;
import java.util.Date;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<MyUser> {

    MyUser findMyUsersByName(String name);

    MyUser findMyUsersByEmail(String email);

    MyUser findMyUsersById(Long id);

    @Modifying
    @Query("update MyUser user set user.password = ?2 where user .id = ?1")
    void updateMyUserPass(Long id, String pass);

    @Modifying
    @Query("update MyUser user set user.name = ?2, user.sex = ?3, user.city = ?4, user.description = ?5, user.updateDate = ?6 where user.id = ?1")
    void updateUserInfo( Long id, String name, String sex, String city, String description, Date updateDate);
}
