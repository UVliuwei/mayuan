package com.myuan.web.service;

import com.myuan.web.dao.UserDao;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author liuwei
 * @date 2018/1/19 18:04
 * user业务层
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户名查用户
     */
    public MyUser getUserByName(String name) {
        return userDao.findMyUsersByName(name);
    }

    /**
     * 邮箱查用户
     */
    public MyUser getUserByEmail(String email) {
        return userDao.findMyUsersByEmail(email);
    }
    @Transactional(readOnly = false)
    public MyResult saveUser(MyUser user) {
        userDao.save(user);
        return MyResult.action("/user/login","注册成功");
    }

}
