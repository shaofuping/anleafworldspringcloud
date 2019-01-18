package com.anleafworld.springcloud.springclouddemoclient.controller;

import com.anleafworld.springcloud.springclouddemoclient.apimodel.WidelyResult;
import com.anleafworld.springcloud.springclouddemoclient.service.FastDFSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@Api(value = "文件上传接口", description = "文件上传调用")
public class UploadfileController {
    @Autowired
    private FastDFSService service;

    @PostMapping( value = "/picture", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "uploadfile", notes = "小程序调用上传文件")
    public WidelyResult uploadPicture(MultipartFile file) {
        return service.uploadPicture(file);
    }
}
