package com.myuan.web.controller;
/*
 * @author liuwei
 * @date 2018/1/27 12:35
 *
 */

import com.myuan.web.entity.MyResult;
import org.springframework.validation.BindingResult;

public class BaseController {

    /**
     * 表单验证
     * <liuwei> [2018/1/27 12:41]
     */
    protected MyResult validForm(BindingResult result) {
            String message = result.getFieldError().getDefaultMessage();
            return MyResult.error(message);
    }
}
