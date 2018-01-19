package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:31
 * 发帖类
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MyPost extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8830292656435150109L;

    private String userId;

    private String title;

    private String ptype;

    private String version;

    private String pcolumn;

    private Integer kiss;

    private Integer ansnum;

    private Integer popularity;

    //加精
    private String boutiqued;

    private String topped;

    private String ended;

    private String accepted;

    private String content;
    //发帖人
//    private FlyUser user;
}
