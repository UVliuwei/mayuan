package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:31
 * 发帖类
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class MyPost extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8830292656435150109L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //自增长主键
    private Long id;

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

    //发帖人,不加入数据库
    @Transient
    private MyUser user;
}
