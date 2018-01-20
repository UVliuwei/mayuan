package com.myuan.web.service;

import com.myuan.web.dao.UserDao;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import lombok.extern.log4j.Log4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author liuwei
 * @date 2018/1/19 18:04
 * user业务层
 */
@Log4j
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

    /**
     * 用户注册
     */

    @Transactional(readOnly = false)
    public MyResult saveUser(MyUser user) {

        if (getUserByName(user.getName()) != null) {
            return MyResult.error("用户名已被注册");
        }
        if (getUserByEmail(user.getEmail()) != null) {
            return MyResult.error("邮箱已被注册");
        }
        user.preInsert();
        user.setImg("1");
        user.setKiss(200);
        user.setLocked("0");
        userDao.save(user);
        log.info("用户：" + user.getName() + "注册成功");
        return MyResult.action("/user/login", "注册成功");
    }

}
