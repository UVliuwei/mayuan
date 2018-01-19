package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:35
 *  签到类
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class MySign extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1662749780595220392L;

    private String signed;

    private Integer continueNum;

    private Integer signNum;

    private Date lastDate;
}
