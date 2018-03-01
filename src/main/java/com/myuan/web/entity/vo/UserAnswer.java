package com.myuan.web.entity.vo;
/*
 * @author liuwei
 * @date 2018/2/27 14:31
 * 红谷回答次数
 */

import com.myuan.web.entity.MyUser;
import lombok.Data;

@Data
public class UserAnswer {

    private MyUser user;
    private Integer count;
    private boolean zan;
}
