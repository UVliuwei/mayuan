package com.myuan.web.test;

import com.myuan.web.WebApplication;
import com.myuan.web.dao.UserDao;
import com.myuan.web.entity.MyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/*
 * @author liuwei
 * @date 2018/1/19 18:17
 *  dao层测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration
public class DaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testDao() {
        MyUser user = userDao.findMyUsersByName("宿久丶");
    }
}
