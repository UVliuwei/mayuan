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

import java.util.Date;

@Getter
@Setter
public class BaseEntity {
    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    //格式化的日期
    private String time;

    /**
     * 插入操作时手动调用
     */
    public void preInsert() {
        String id = IdUtil.getId();
        this.id=id;
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
