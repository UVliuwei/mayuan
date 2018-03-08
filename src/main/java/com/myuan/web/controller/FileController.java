package com.myuan.web.controller;

import com.myuan.web.entity.MyResult;
import com.myuan.web.entity.MyUser;
import com.myuan.web.service.UploadService;
import com.myuan.web.utils.UserUtil;
import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*
 * @author liuwei
 * @date 2018/3/7 13:37
 *
 */
@RestController
@RequestMapping("api")
@Api("文件上传接口")
public class FileController extends BaseController{

    @Autowired
    private UploadService uploadService;

    @PostMapping("file/upload")
    public MyResult upload(MultipartFile file) {
        MyResult result = uploadService.uploadFile(file);
        if("1".equals(result.getStatus())) {
            MyUser currentUser = UserUtil.getCurrentUser();
            currentUser.setImg(result.getData().toString());
            setUserSession(currentUser);
        }
        return result;
    }
}
