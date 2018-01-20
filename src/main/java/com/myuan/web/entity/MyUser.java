package com.myuan.web.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import org.hibernate.validator.constraints.Email;

/*
 * @author liuwei
 * @date 2018/1/19 16:14
 *  用户类
 */
@Data
@Entity
public class MyUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5615703065557959847L;

    //自增长主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sex;

    private String password;
    @Email
    private String email;

    private String city;

    private String img;

    private Integer kiss;

    private String locked;

    private String description;

}
