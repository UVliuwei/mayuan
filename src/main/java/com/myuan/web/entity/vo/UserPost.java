package com.myuan.web.entity.vo;

import com.myuan.web.entity.MyPost;
import com.myuan.web.entity.MyUser;
import java.io.Serializable;
import lombok.Data;

/*
 * @author liuwei
 * @date 2018/2/27 10:48
 * 用户帖子视图类
 */
@Data
public class UserPost implements Serializable{

    private MyPost post;
    private MyUser user;
}
