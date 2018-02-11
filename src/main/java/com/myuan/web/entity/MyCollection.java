package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:26
 * 收藏类
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
@Data
@Entity
public class MyCollection extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8218131001857860120L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //自增长主键
    private Long id;

    private Long userId;

    private Long postId;

}
