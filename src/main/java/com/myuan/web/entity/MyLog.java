package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:30
 * 日志类
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
public class MyLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7453661907419536307L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //自增长主键
    private Long id;

    private String type;

    private String title;

    private String remoteAddr;

    private String requestUrl;

    private String method;

    private String params;

    private String exception;
}
