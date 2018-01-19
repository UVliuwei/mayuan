package com.myuan.web.service;

import com.myuan.web.dao.UserDao;
import com.myuan.web.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author liuwei
 * @date 2018/1/19 18:04
 * user业务层
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public MyUser getUserByName(String name) {
        MyUser user = userDao.findMyUsersByName(name);
        return user;
    }
}
