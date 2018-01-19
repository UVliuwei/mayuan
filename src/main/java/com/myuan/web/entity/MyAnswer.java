package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 16:48
 *  回答类
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Getter
@Setter
@Entity
public class MyAnswer extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 3523832476535463710L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //自增长主键
    private Long id;

    private String postId;

    private String userId;

    private String userImg;

    private String userName;

    private Integer likes;

    private String content;
}
