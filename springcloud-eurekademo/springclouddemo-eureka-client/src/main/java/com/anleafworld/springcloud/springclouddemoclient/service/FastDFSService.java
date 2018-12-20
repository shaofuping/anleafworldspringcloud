package com.anleafworld.springcloud.springclouddemoclient.service;

import com.anleafworld.springcloud.springclouddemoclient.apimodel.WidelyResult;
import com.anleafworld.springcloud.springclouddemoclient.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FastDFSService {
    @Value("${fdfs.resHost}")
    private String resHost;

    public WidelyResult uploadPicture(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            FastDFSClient client = new FastDFSClient(resHost);
            String upresult = client.uploadFile(file.getBytes(), ext);
            return WidelyResult.build(200, "上传成功", upresult);
        } catch (Exception e) {
            e.printStackTrace();
            return WidelyResult.build(500, "上传失败");
        }
    }
}
