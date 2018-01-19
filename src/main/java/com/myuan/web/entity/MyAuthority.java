package com.myuan.web.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/*
 * @author liuwei
 * @date 2018/1/19 16:58
 *  权限类
 */
@Getter
@Setter
@Entity
public class MyAuthority extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4710757893851847835L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //自增长主键
    private Long id;

    private String name;

    private String auth;
}
