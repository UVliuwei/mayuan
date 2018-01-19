package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:34
 * 角色类
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class MyRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 6227154819811973488L;

    private String name;

    private String type;
}
