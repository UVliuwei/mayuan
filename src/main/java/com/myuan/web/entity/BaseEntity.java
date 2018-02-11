package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 16:16
 *
 */

import com.myuan.web.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

//子类包含本类的属性
@MappedSuperclass
public class BaseEntity {
    @Getter @Setter
    protected Date createDate;
    @Getter @Setter
    protected Date updateDate;
    //格式化的日期
    @Transient
    protected String time;

    /**
     * 插入操作时手动调用
     */
    public void preInsert() {
        this.createDate = new Date();
        this.updateDate = new Date();
    }
    /**
     * 更新前操作时手动调用
     */
    public void preUpdate() {
        this.updateDate = new Date();
    }

    public String getTime() {
        return DateUtil.getDate(getCreateDate());
    }
}
