package com.myuan.web.service;

import com.myuan.web.entity.MyResult;
import com.myuan.web.utils.IDUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/*
 * @author liuwei
 * @date 2018/3/7 13:44
 * 图片上传
 */
@Service
@Log4j
public class UploadService {

    @Value("${image.server.url}")
    private String FILE_PATH;

    public MyResult uploadFile(MultipartFile file) {

        try {
            String oldName = file.getOriginalFilename();
            String newName = IDUtil.getImageId();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            Client client = Client.create();
            WebResource resource = client.resource(FILE_PATH + newName);
            String response = resource.put(String.class, file.getBytes());
            if("error".equals(response)) {
                throw new Exception();
            }
            return MyResult.data(newName);

        } catch (Exception e) {
            log.error("图片上传出错：" + e.toString());
        }
    return MyResult.error("头像上传出错,请重试");
    }
}
