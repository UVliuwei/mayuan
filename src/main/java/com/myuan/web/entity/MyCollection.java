package com.myuan.web.entity;
/*
 * @author liuwei
 * @date 2018/1/19 17:26
 * 收藏类
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class MyCollection implements Serializable {

    private static final long serialVersionUID = -8218131001857860120L;

    private String userId;

    private String postId;

}
