package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 16:16
 *
 */

import com.myuan.web.utils.IdUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
//子类包含本类的属性
@MappedSuperclass
public class BaseEntity {

    protected Date createDate;
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
}
