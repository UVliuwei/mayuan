package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:35
 *  签到类
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
public class MySign extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1662749780595220392L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //自增长主键
    private Long id;

    private String signed;

    private Integer continueNum;

    private Integer signNum;

    private Date lastDate;
}
