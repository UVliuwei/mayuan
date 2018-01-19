package com.myuan.web.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
 * @author liuwei
 * @date 2018/1/19 16:58
 *  权限类
 */
@Getter
@Setter
public class MyAuthority extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4710757893851847835L;

    private String name;

    private String auth;
}
