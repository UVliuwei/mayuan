package com.myuan.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
 * @author liuwei
 * @date 2018/1/19 16:14
 *  用户类
 */
@Getter
@Setter
public class MyUser extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 5615703065557959847L;

    private String name;

    private String sex;

    private String password;

    private String email;

    private String city;

    private String img;

    private Integer kiss;

    private String locked;

    private String description;
}
