package com.myuan.web.service;

import com.myuan.web.dao.UserDao;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyRole;
import com.myuan.web.entity.MyUser;
import lombok.extern.log4j.Log4j;
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
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;

    /**
     * 用户名查用户
     */
    public MyUser getUserByName(String name) {
        return userDao.findMyUsersByName(name);
    }

    /**
     * 用户ID查用户
     */
    public MyUser getUserById(Long id) {
        return userDao.findMyUsersById(id);
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
    @Transactional
    public MyResult saveUser(MyUser user) {
        try {
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
            saveUserRole(user.getId());
            return MyResult.action("/user/login", "注册成功");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MyResult.error("系统异常,请重试");
    }

    /**
     * 为新建用户分配 user角色
     */
    @Transactional
    public void saveUserRole(Long userId) {
        MyRole role = roleService.findRoleByType("user");
        roleService.saveUserRole(userId, role.getId());
    }

}
