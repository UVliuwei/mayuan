package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 16:48
 *  回答类
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class MyAnswer extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 3523832476535463710L;

    private String postId;

    private String userId;

    private String userImg;

    private String userName;

    private Integer likes;

    private String content;
}
