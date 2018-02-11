package com.myuan.web.service;

import com.myuan.web.dao.UserDao;
import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyRole;
import com.myuan.web.entity.MyUser;
import com.myuan.web.utils.SaltPasswordUtil;
import java.util.Date;
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
            user.setImg("1.jpg");
            user.setKiss(200);
            user.setLocked("0");
            userDao.save(user);

            log.info("用户：" + user.getName() + "注册成功");
            saveUserRole(user.getId());
            return MyResult.action("/user/login", "注册成功");
        } catch (Exception ex) {
            log.info("用户注册更新异常: " + ex.toString());
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

    /**
     * 更新用户密码
     */
    @Transactional
    public MyResult updateUserPass(Long id, String pass) {
        String newPass = SaltPasswordUtil.getNewPassword(pass);
        userDao.updateMyUserPass(id, newPass);
        return MyResult.ok("密码修改成功");
    }

    /**
     * 更新用户信息
     */
    public MyResult updateUserInfo(Long id, String name, String sex, String city, String description) {
        try {
            MyUser user = getUserByName(name);
            if (user != null && !user.getId().equals(id)) {
                return MyResult.error("用户名已存在");
            }
            city = city == "" || city == null ? "隐藏" : city;
            userDao.updateUserInfo(id, name, sex, city, description, new Date());
            return MyResult.ok("信息更新成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("用户信息更新异常");
        }
        return MyResult.error("系统异常，请重试");
    }
}
