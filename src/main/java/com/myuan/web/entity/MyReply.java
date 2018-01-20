package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:32
 * 回复类
 */

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class MyReply extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1568115594503286370L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //自增长主键
    private Long id;

    private String userId;

    private String userName;

    private String postId;

    private String postName;

    private Date repltTime;

    private String time;

    private String replyName;

}
