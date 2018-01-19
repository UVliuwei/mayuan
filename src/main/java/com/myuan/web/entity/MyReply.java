package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:32
 * 回复类
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class MyReply extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1568115594503286370L;

    private String userId;

    private String userName;

    private String postId;

    private String postName;

    private Date repltTime;

    private String time;

    private String replyName;

}
